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
@Table(name = "GAME_SET") // set is a reserved word
public class Set {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Match match;

}
