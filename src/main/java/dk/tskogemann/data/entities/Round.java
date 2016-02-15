package dk.tskogemann.data.entities;

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
public class Round {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Tournament tournament;

    @Column
    private int round;


    @OneToMany(mappedBy = "round", cascade = CascadeType.PERSIST)
    private Collection<Match> matches;

    protected Round() {
    }

    public Round(Tournament tournament, int currentRound) {
        this.tournament = tournament;
        round = currentRound;
    }

    public Match createMatch() {
        return new Match(this);
    }
}
