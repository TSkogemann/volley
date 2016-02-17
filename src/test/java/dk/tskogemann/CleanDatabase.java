package dk.tskogemann;

import dk.tskogemann.data.JPAConfig;
import dk.tskogemann.data.entities.Tournament;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * @author Klaus Groenbaek
 */
public class CleanDatabase {


    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(JPAConfig.class);
        ctx.refresh();

        EntityManagerFactory emf = ctx.getBean(EntityManagerFactory.class);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        for (Tournament tournament : em.createQuery("select t from Tournament t", Tournament.class).getResultList()) {
            em.remove(tournament);
        }

        em.getTransaction().commit();

    }

}
