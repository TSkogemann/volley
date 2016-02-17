package dk.tskogemann.web.controllers;

import dk.tskogemann.data.entities.Round;
import dk.tskogemann.data.entities.Tournament;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Klaus Groenbaek
 */
@Controller
@RequestMapping("/round")
public class RoundController {

    @PersistenceContext
    private EntityManager em;

    @RequestMapping(value = "/list")
    public ModelAndView listRounds(@RequestParam("tournamentId") long tournamentId) {

        Tournament tournament = em.find(Tournament.class, tournamentId);
        ModelAndView mv = new ModelAndView("round/list");
        mv.addObject("tournaments", tournament.getRounds());
        return mv;
    }

    @RequestMapping(value = "/view")
    public ModelAndView view(@RequestParam("roundId") long roundId) {
        Round round = em.find(Round.class, roundId);
        ModelAndView mv = new ModelAndView("round/view");
        mv.addObject("round", round);
        return mv;
    }

}
