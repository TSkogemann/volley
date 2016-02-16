package dk.tskogemann.web.controllers;

import com.google.common.collect.Lists;
import dk.tskogemann.data.entities.Player;
import dk.tskogemann.data.entities.Tournament;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Random;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/**
 * @author Klaus Groenbaek
 */
@Controller
@RequestMapping("/example")
public class ExampleController {

    private List<String> firstNames = Lists.newArrayList("Ole", "Thomas", "Anders", "Klaus", "Peter", "Henrik", "Kristian",
            "Marianne", "Sofie", "Emil", "Louise", "Mette", "Jette", "Jesper", "Ulrik", "Stefan", "Palle", "Simon", "Rebekka");

    private List<String> lastNames = Lists.newArrayList("Kristensen", "Olsen", "Hansen", "Nielsen", "Poulsen", "Bæk", "Kierkegaard",
            "Sørnsen", "Larsen", "Jørgensen");


    @PersistenceContext
    private EntityManager em;

    /**
     * Generates a number of random players, there may be duplicates
     * @param players te number of players to generate
     */
    @RequestMapping(path = "/createPlayers", method = RequestMethod.GET)
    public String createPlayers(@RequestParam("count") int players ) {

        Random rnd = new Random();
        em.getTransaction().begin();
        for (int i= 0; i< players; i++) {
            String firstName = firstNames.get(rnd.nextInt(firstNames.size()));
            String lastName = lastNames.get(rnd.nextInt(lastNames.size()));
            Player p = new Player().setFirstName(firstName).setLastName(lastName);
            em.persist(p);
        }

        em.getTransaction().commit();
        return "redirect:index";
    }



}
