package com.ct5106.swishStats.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Team
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamid;

    private String teamname;
    private int standing;
    private int wins;
    private int draws;
    private int losses;
    private int teamrating;

    @ManyToOne
    @JoinColumn(name = "league_id")
    private League league;

    public Team()
    {
    }
    public Team(String teamname, int standing, int wins, int draws, int losses, int rating)
    {
        super();
        this.teamname = teamname;
        this.standing = standing;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
        this.teamrating = rating;

    }

    public Long getTeamid() {
        return teamid;
    }

    public void setTeamid(Long teamid) {
        this.teamid = teamid;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public int getStanding() {
        return standing;
    }

    public void setStanding(int standing) {
        this.standing = standing;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getTeamRating() {
        return teamrating;
    }

    public void setTeamrating(int teamrating) {
        this.teamrating = teamrating;
    }
    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }


    public League getConference() {
        return league;
    }

    public void setConference(League league) {
        this.league = league;
    }
}
