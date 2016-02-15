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
public class TeamParticipation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @ManyToOne(optional = false)
    private Player player;

    @Column(nullable = false)
    @ManyToOne(optional = false)
    private Team team;

    public TeamParticipation(Team team, Player player) {
        this.team = team;
        this.player = player;
    }

    public TeamParticipation() {
    }
}
