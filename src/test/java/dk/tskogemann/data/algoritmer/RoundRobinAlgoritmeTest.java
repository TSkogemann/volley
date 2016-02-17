package dk.tskogemann.data.algoritmer;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.function.Function;

/**
 * Created by thomas on 17-02-2016.
 */
public class RoundRobinAlgoritmeTest {

    @Test
    public void test27PlayersOn3Courts(){
        // max players = 6, min players=5, courts = 3
int numberOfPlayers = 27;
        RoundRobinAlgoritme test = new RoundRobinAlgoritme(numberOfPlayers,6,5,3);
        ArrayList<Integer> result = test.createRound();
        ArrayList<Integer> expResult = new ArrayList<>();
        expResult.add(6);
        expResult.add(6);
        expResult.add(6);
        expResult.add(6);
        Assert.assertEquals(expResult, result);
        Assert.assertTrue("number of players", numberOfPlayers >= expResult.stream().mapToInt(i-> i).sum());
    }

    @Test
    public void test30playersOn3Courts(){
        // max players = 6, min players=5, courts = 3
int numberOfPlayers = 30;
        RoundRobinAlgoritme test = new RoundRobinAlgoritme(numberOfPlayers,6,5,3);
        ArrayList<Integer> result = test.createRound();
        ArrayList<Integer> expResult = new ArrayList<>();
        expResult.add(5);
        expResult.add(5);
        expResult.add(5);
        expResult.add(5);
        expResult.add(5);
        expResult.add(5);
        Assert.assertEquals(expResult, result);
        Assert.assertTrue("number of players", numberOfPlayers >= expResult.stream().mapToInt(i-> i).sum());
    }
    @Test
    public void test34playersOn3Courts(){
        // max players = 6, min players=5, courts = 3

        int numberOfPlayers = 34;
        RoundRobinAlgoritme test = new RoundRobinAlgoritme(numberOfPlayers,6,5,3);
        ArrayList<Integer> result = test.createRound();
        ArrayList<Integer> expResult = new ArrayList<>();
        expResult.add(6);
        expResult.add(6);
        expResult.add(6);
        expResult.add(6);
        expResult.add(5);
        expResult.add(5);
        Assert.assertEquals(expResult, result);

        Assert.assertTrue("number of players", numberOfPlayers >= expResult.stream().mapToInt(i -> i).sum());
    }
    @Test
    public void test100playersOn3Courts(){
        // max players = 6, min players=5, courts = 3

        int numberOfPlayers = 100;
        RoundRobinAlgoritme test = new RoundRobinAlgoritme(numberOfPlayers,6,5,3);
        ArrayList<Integer> result = test.createRound();
        ArrayList<Integer> expResult = new ArrayList<>();
        expResult.add(6);
        expResult.add(6);
        expResult.add(6);
        expResult.add(6);
        expResult.add(6);
        expResult.add(6);
        Assert.assertEquals(expResult, result);

        Assert.assertTrue("number of players", numberOfPlayers >= expResult.stream().mapToInt(i -> i).sum());
    }
}
