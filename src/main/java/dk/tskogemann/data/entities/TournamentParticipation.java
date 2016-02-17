package dk.tskogemann.data.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Klaus Groenbaek
 *         Created 15/02/16.
 */
@Entity
@Getter
@Setter
public class TournamentParticipation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(nullable = false)
    @ManyToOne(optional = false)
    private Player player;

    @JoinColumn(nullable = false)
    @ManyToOne(optional = false)
    private Tournament tournament;

    public TournamentParticipation() {}

    public TournamentParticipation(Tournament tournament, Player player) {
        this.tournament = tournament;
        this.player = player;
    }
}
