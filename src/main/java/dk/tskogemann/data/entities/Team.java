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
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private Collection<TeamParticipation> players;

    @ManyToOne
    private Match match;

    public Team() {

    }

    public Team(Match match) {
        this.match = match;
    }

    public Collection<TeamParticipation> getPlayers() {
        if (players == null) {
            players = new ArrayList<>();
        }
        return players;
    }

    public void addPlayer(Player player) {
       TeamParticipation participation = new TeamParticipation(this, player);
       getPlayers().add(participation);
    }
}
