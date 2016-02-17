package dk.tskogemann.web.controllers;

import dk.tskogemann.data.entities.Player;
import dk.tskogemann.data.entities.Position;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Klaus Groenbaek
 */
@Controller
@RequestMapping(value = "/player")
public class PlayerController {

    @PersistenceContext
    private EntityManager em;

    @RequestMapping(value = "/list")
    public ModelAndView list() {

        List<Player> players = em.createQuery("select p from Player p", Player.class).getResultList();
        ModelAndView mv = new ModelAndView("player/list");
        mv.addObject("players", players);

        return mv;
    }

    @RequestMapping(value = "/add")
    @Transactional
    public String add(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                      @RequestParam(value = "position", required = false) Position[] positions) {

        Player p = new Player().setFirstName(firstName).setLastName(lastName);
        for (Position position : positions) {
            p.addPosition(position);
        }
        em.persist(p);
        return "forward:/player/list";
    }

    @RequestMapping(value = "/delete")
    @Transactional
    public String delete(@RequestParam("id") long id) {

        Player player = em.find(Player.class, id);
        em.remove(player);
        return "forward:/player/list";
    }


}
