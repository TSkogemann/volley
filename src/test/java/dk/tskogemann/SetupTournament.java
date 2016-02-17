package dk.tskogemann;

import com.google.common.collect.Lists;
import dk.tskogemann.data.JPAConfig;
import dk.tskogemann.data.entities.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Simple way of creating a tournament with a single round and some players
 * @author Klaus Groenbaek
 */
public class SetupTournament {

    private static List<String> firstNames = Lists.newArrayList("Ole", "Thomas", "Anders", "Klaus", "Peter", "Henrik", "Kristian",
            "Marianne", "Sofie", "Emil", "Louise", "Mette", "Jette", "Jesper", "Ulrik", "Stefan", "Palle", "Simon", "Rebekka");

    private static List<String> lastNames = Lists.newArrayList("Kristensen", "Olsen", "Hansen", "Nielsen", "Poulsen", "Bæk", "Kierkegaard",
            "Sørnsen", "Larsen", "Jørgensen");

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(JPAConfig.class);
        ctx.refresh();

        EntityManagerFactory emf = ctx.getBean(EntityManagerFactory.class);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();


        List<Player> players = em.createQuery("select p from Player p", Player.class).getResultList();
        int playersInTournament = 36;
        int playersToCreate = playersInTournament - players.size();

        Random rnd = new Random();
        Tournament t = new Tournament();
        t.setTournamentStart(new Date(LocalDateTime.of(2016, Month.APRIL, 25, 9, 0).toEpochSecond(ZoneOffset.UTC)));
        t.setName("Test Tournament");
        for (int i = 0; i < playersToCreate; i++) {
            String firstName = firstNames.get(rnd.nextInt(firstNames.size()));
            String lastName = lastNames.get(rnd.nextInt(lastNames.size()));
            Player p = new Player().setFirstName(firstName).setLastName(lastName);
            em.persist(p);
            players.add(p);
        }

        for (int i = 0; i < playersInTournament; i++) {
            Player player = players.get(i);
            t.addPlayer(player);
        }

        Round round = t.newRound();


        ArrayList<TournamentParticipation> tournamentParticipations = new ArrayList<>(t.getTournamentParticipants());
        Team team = null;
        Match match = null;
        for (int i = 0; i < tournamentParticipations.size(); i++) {
            if (i % 12 == 0) {
                match = round.createMatch();
            }
            if (i % 6 == 0) {
                team  = match.addTeam();
            }
            TournamentParticipation participant = tournamentParticipations.get(i);
            team.addPlayer(participant.getPlayer());
        }
        em.persist(t);
        em.getTransaction().commit();


    }

}
