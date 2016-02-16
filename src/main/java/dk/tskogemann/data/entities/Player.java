package dk.tskogemann.data.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import com.google.common.base.Joiner;
import lombok.Data;
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
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50)
    private String firstName;

    @Column(length = 50)
    private String lastName;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private Collection<PlayerPosition> positions;

    @OneToMany(mappedBy = "player", cascade = CascadeType.REFRESH)
    private Collection<TeamParticipation> teams;

    @OneToMany(mappedBy = "player", cascade = CascadeType.REFRESH)
    private Collection<TournamentParticipation> tournamentParticipations;


    public Collection<PlayerPosition> getPositions() {
        if (positions == null) {
            positions = new ArrayList<>();
        }
        return positions;
    }

    public Player addPosition(Position position) {
        getPositions().add(new PlayerPosition(this, position));
        return this;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getPositionsAsString() {
        return Joiner.on(",").join(getPositions());
    }
}
