package dk.tskogemann.data.entities;

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
public class TournamentParticipation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @ManyToOne(optional = false)
    private Player player;

    @Column(nullable = false)
    @ManyToOne(optional = false)
    private Tournament tournament;

    public TournamentParticipation() {}

    public TournamentParticipation(Tournament tournament, Player player) {
        this.tournament = tournament;
        this.player = player;
    }
}
