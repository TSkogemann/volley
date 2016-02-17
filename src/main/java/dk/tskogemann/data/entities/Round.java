package dk.tskogemann.data.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

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


    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL)
    private Collection<Match> matches;

    protected Round() {
    }

    public Round(Tournament tournament, int currentRound) {
        this.tournament = tournament;
        round = currentRound;
    }

    public Collection<Match> getMatches() {
        if (matches == null) {
            matches = new ArrayList<>();
        }
        return matches;
    }

    public Match createMatch() {
        Match match = new Match(this);
        getMatches().add(match);
        return match;
    }
}
