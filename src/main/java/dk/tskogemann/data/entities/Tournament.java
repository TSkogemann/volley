package dk.tskogemann.data.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Klaus Groenbaek
 *         Created 15/02/16.
 */
@Entity
@Getter
@Setter
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "tournament")
    private Collection<TournamentParticipation> tournamentParticipants;

    @OneToMany(mappedBy = "tournament")
    private Collection<Round> rounds;

    @Column
    private int currentRound;

    public Collection<TournamentParticipation> getTournamentParticipants() {
        if (tournamentParticipants == null){
            tournamentParticipants = new ArrayList<>();
        }
        return tournamentParticipants;
    }

    public Collection<Round> getRounds() {
        if (rounds == null) {
            rounds = new ArrayList<>();
        }
        return rounds;
    }

    public void addPlayer(Player player) {
        getTournamentParticipants().add(new TournamentParticipation(this, player));
    }

    public Round newRound() {
        currentRound++;
        Round round = new Round(this, currentRound);
        getRounds().add(round);
        return round;
    }
}
