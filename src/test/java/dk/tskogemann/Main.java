package dk.tskogemann;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import dk.tskogemann.data.JPAConfig;
import dk.tskogemann.data.entities.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.Collection;
import java.util.List;

/**
 * @author Klaus Groenbaek
 *         Created 15/02/16.
 */
public class Main {
    public static void main(String[] args) {

        MysqlDataSource ds = new MysqlDataSource();
        ds.setUser("root");
        ds.setPassword("password");
        ds.setURL("jdbc:mysql://localhost:3306/volley");


        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(JPAConfig.class);
        ctx.getBeanFactory().registerSingleton("ds", ds);
        ctx.refresh();

        EntityManagerFactory emf = ctx.getBean(EntityManagerFactory.class);

        Tournament t = new Tournament();
        Player p = new Player().setFirstName("Klaus").setLastName("Grønbæk");
        p.addPosition(Position.LIBERO).addPosition(Position.MIDDLE).addPosition(Position.OUTSIDE);
        t.addPlayer(p);
        Round r = t.newRound();
        Match m = r.createMatch();
        Team team = m.addTeam();
        team.addPlayer(p);

        EntityManager entityManager = emf.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(t);
            entityManager.persist(p);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }

        entityManager = emf.createEntityManager();
        t = entityManager.find(Tournament.class, t.getId());
        System.out.println(t);

        List<Player> resultList = entityManager.createQuery("select p from Player p where p.firstName=:name", Player.class)
                .setParameter("name", "Klaus").getResultList();

        for (Player player : resultList) {
            System.out.println("player = " + player);
        }
    }
}
