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
public class TeamParticipation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(nullable = false)
    @ManyToOne(optional = false)
    private Player player;

    @JoinColumn(nullable = false)
    @ManyToOne(optional = false)
    private Team team;

    public TeamParticipation(Team team, Player player) {
        this.team = team;
        this.player = player;
    }

    public TeamParticipation() {
    }
}
