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
public class PlayerPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Player player;

    @Column
    @Enumerated(EnumType.STRING)
    private Position position;


    public PlayerPosition() {

    }

    public PlayerPosition(Player player, Position position) {
        this.position = position;
        this.player = player;
    }

    @Override
    public String toString() {
        return position.toString();
    }
}
