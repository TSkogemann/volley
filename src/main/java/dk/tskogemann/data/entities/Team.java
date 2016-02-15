package dk.tskogemann.data.entities;

import java.util.ArrayList;
import java.util.Collection;

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
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "team", cascade = CascadeType.PERSIST)
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
