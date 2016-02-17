package dk.tskogemann.web.controllers;

import dk.tskogemann.data.entities.Player;
import dk.tskogemann.data.entities.Round;
import dk.tskogemann.data.entities.Tournament;
import dk.tskogemann.data.entities.TournamentParticipation;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author Klaus Groenbaek
 */
@Controller
@RequestMapping("/tournament")
public class TournamentController {

    @PersistenceContext
    private EntityManager em;

    @RequestMapping(value = "/list")
    public ModelAndView listTournaments() {

        List<Tournament> list = em.createQuery("select t from Tournament t", Tournament.class).getResultList();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("tournament/list");
        mv.addObject("tournaments", list);
        return mv;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @Transactional
    public String createTournament(@RequestParam("name") String name, @RequestParam("date") String dateString) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date startDate = df.parse(dateString);
        Tournament t = new Tournament().setName(name).setTournamentStart(startDate);
        em.persist(t);
        return "forward:/tournament/list";
    }

    @RequestMapping(value = "/view")
    public ModelAndView view(@RequestParam("id") long id) {
        Tournament tournament = em.find(Tournament.class, id);

        // use sub query to find all players already in the tournament, and a NOT IN to exclude them
        List<Player> available = em.createQuery("select p from Player p where p not in " +
                "(select t.player from TournamentParticipation t where t.tournament.id=:tournamentId)", Player.class)
                .setParameter("tournamentId", id).getResultList();



        ModelAndView mv = new ModelAndView("tournament/view");
        mv.addObject("tournament", tournament);
        mv.addObject("available", available);
        return mv;
    }

    @RequestMapping(value = "/addPlayer", method = RequestMethod.GET)
    @Transactional
    public String addPlayer(@RequestParam("id") long id, @RequestParam("playerId") long playerId) throws Exception {

        Tournament tournament = em.find(Tournament.class, id);
        Player player = em.find(Player.class, playerId);
        tournament.addPlayer(player);
        em.persist(tournament);
        return "forward:/tournament/view?id=" + id;
    }

    @RequestMapping(value = "/removePlayer", method = RequestMethod.GET)
    @Transactional
    public String removePlayer(@RequestParam("id") long id, @RequestParam("playerId") long playerId) throws Exception {

        Tournament tournament = em.find(Tournament.class, id);
        Player player = em.find(Player.class, playerId);
        for (Iterator<TournamentParticipation> iterator = tournament.getTournamentParticipants().iterator(); iterator.hasNext(); ) {
            TournamentParticipation participation = iterator.next();
            if (participation.getPlayer() == player) {
                iterator.remove();
                em.remove(participation);
                break;
            }
        }

        return "forward:/tournament/view?id=" + id;
    }

    @RequestMapping(value = "/newRound", method = RequestMethod.GET)
    @Transactional
    public String newRound(@RequestParam("tournamentId") long tournamentId) {

        Tournament tournament = em.find(Tournament.class, tournamentId);
        Round round = tournament.newRound();
        em.persist(tournament);

        return "forward:/round/view?roundId=" + round.getId();
    }

}
