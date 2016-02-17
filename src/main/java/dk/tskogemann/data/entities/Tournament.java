package dk.tskogemann.data.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author Klaus Groenbaek
 *         Created 15/02/16.
 */
@Entity
@Getter
@Setter
@Accessors(chain = true)
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.PERSIST)
    private Collection<TournamentParticipation> tournamentParticipants;

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.PERSIST)
    private Collection<Round> rounds;

    @Column
    private int currentRound;

    @Column
    private String name;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date tournamentStart;



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


