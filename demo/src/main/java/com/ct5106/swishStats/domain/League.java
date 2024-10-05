package com.ct5106.swishStats.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class League
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "league", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("league")
    private List<Team> teams;
    public League()
    {
        // super();
    }

    public League(String name)
    {
        super();
        this.name = name;
    }

    public Long getId()
    {
        return id;
    }

    // this should only be set in the DB, so commenting this one out
    /*
     * public void setId(Long id) { this.id = id; }
     */

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }


    // Getter and setter
    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}
