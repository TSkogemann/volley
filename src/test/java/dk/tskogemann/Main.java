package dk.tskogemann;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import dk.tskogemann.data.JPAConfig;
import dk.tskogemann.data.entities.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Klaus Groenbaek
 *         Created 15/02/16.
 */
public class Main {
    public static void main(String[] args) {




        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(JPAConfig.class);
        ctx.refresh();


        MysqlDataSource ds = new MysqlDataSource();
        ds.setUser("root");
        ds.setPassword("password");
        ds.setURL("jdbc:mysql://localhost:3306/volley");

        Tournament t = new Tournament();
        Player p = new Player().setFirstName("Klaus").setLastName("Grønbæk");
        p.addPosition(Position.LIBERO).addPosition(Position.MIDDLE).addPosition(Position.OUTSIDE);
        t.addPlayer(p);
        Round r = t.newRound();
        Match m = r.createMatch();
        Team team = m.addTeam();
        team.addPlayer(p);

        System.out.println(t);




    }
}
