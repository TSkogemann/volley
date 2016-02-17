package dk.tskogemann.data.algoritmer;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by thomas on 17-02-2016.
 */
public class RoundRobinAlgoritm {
    int numberOfPlayers;
    int teamSizeMin;
    int teamSizeMax;
    int courtCount;


    public RoundRobinAlgoritm(int numberOfPlayers, int teamSizeMax, int teamSizeMin, int courtCount) {
        this.numberOfPlayers = numberOfPlayers;
        this.teamSizeMax = teamSizeMax;
        this.teamSizeMin = teamSizeMin;
        this.courtCount = courtCount;
    }

    public ArrayList<Integer> createRound() {
        // udkast til at lave runder
        //preferedteamsize = ?
        // allowedUneventeams?
        // fixed amount of teams?
        // 1. måske to algoritmer, hvor dene ene laver max antal hold af optimal players + et leftover hold ?
        // 2. laver hold til alle baner af min antal spiller op til max.

        ArrayList<Integer> result = new ArrayList();

        int minPlayers = 2 * courtCount * teamSizeMin;
        int maxPlayers = 2 * courtCount * teamSizeMax;

        //Check if enough players
        checkIfEnoughPlayers(minPlayers);
        int teamsNeeded = courtCount * 2;
        //Check if player counter = max or higher
        Optional<ArrayList<Integer>> optional = checkIfMorePlayersThanCourts(maxPlayers, numberOfPlayers, courtCount);
        if (optional.isPresent()) {
            return optional.get();
        }

        // adds teamSizeMin to all teams
        for (int i = 0; i < teamsNeeded; i++) {
            result.add(teamSizeMin);
        }

        int playersLeft = numberOfPlayers - (teamsNeeded * teamSizeMin);
        int amountOfPlayersNotNeeded = numberOfPlayers - (teamsNeeded * teamSizeMax);
        while (playersLeft > teamsNeeded) {
            if (playersLeft > teamsNeeded) {
                for (int i = 0; i < teamsNeeded; i++) {
                    result.set(i, result.get(i) + 1);
                }
                playersLeft = playersLeft - teamsNeeded;
            }
        }

            for (int i = 0; i < playersLeft; i++) {
                if (result.get(i)< teamSizeMax) {
                    result.set(i, result.get(i) + 1);
                }
            // create round with courtCount = courtCount and teamsize= playersPerTeam  + playersLeft - skal de sidde over eller uneven teams ?
        }

        return result;
    }

    private Optional<ArrayList<Integer>> checkIfMorePlayersThanCourts(int maxPlayers, int numberOfPlayers, int courtCount) {
        if (maxPlayers <= numberOfPlayers) {
            // create round with courtCount = CourtCount and teamSize = maxPlayers
            int teamsNeeded = courtCount * 2;
            ArrayList<Integer> result = new ArrayList();
            for (int i = 0; i < teamsNeeded; i++) {
                result.add(teamSizeMax);
            }
            return Optional.of(result);
        }
        return Optional.empty();
    }

    private void checkIfEnoughPlayers(int minPlayers) {
        if (minPlayers > numberOfPlayers) {
            courtCount = courtCount - 1;
            minPlayers = 2 * courtCount * teamSizeMin;
            checkIfEnoughPlayers(minPlayers);

            //reduce courtCount and run again, courtcount =0  så skal den stoppe og give fejl besked
        }
    }

}

