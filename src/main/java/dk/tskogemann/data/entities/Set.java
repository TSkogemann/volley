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
public class Set {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Match match;

}
