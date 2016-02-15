package dk.tskogemann.data.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Klaus Groenbaek
 *         Created 15/02/16.
 */
@Entity
@Getter
@Setter
@Table(name = "GAME") // Match is a reserved keyword in MYSQL
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;

    @OneToMany(mappedBy = "match", cascade = CascadeType.PERSIST)
    private Collection<Set> sets;


    @OneToMany(mappedBy = "match", cascade = CascadeType.PERSIST)
    private Collection<Team> teams;

    @ManyToOne
    private Round round;


    public Match() {

    }

    public Match(Round round) {
        this.round = round;
    }

    public Collection<Set> getSets() {
        if (sets == null) {
            sets = new ArrayList<>();
        }
        return sets;
    }

    public Collection<Team> getTeams() {
        if (teams == null) {
            teams = new ArrayList<>();
        }
        return teams;
    }

    public Team addTeam() {
        Team team = new Team(this);
        getTeams().add(team);
        return team;
    }
}
