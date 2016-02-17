package dk.tskogemann.data.entities;

import com.google.common.base.Joiner;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

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
@Accessors(chain = true)
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        return Joiner.on(", ").join(getPositions());
    }
}
