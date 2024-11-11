package com.ct5106.swishStats.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Team
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamid;

    private String teamName;
    private int standing;
    private int wins;
    private int draws;
    private int losses;
    private int teamRating;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "league_id")
    private League league;

    @OneToMany(mappedBy = "team", cascade = CascadeType.DETACH)
    @JsonIgnoreProperties("team")
    private List<Player> players;

    @OneToOne(mappedBy = "team", cascade = CascadeType.DETACH)
    @JsonIgnoreProperties("team")
    private Manager manager;
    public Team()
    {
    }
    public Team(String teamName, int standing, int wins, int draws, int losses, int rating)
    {
        super();
        this.teamName = teamName;
        this.standing = standing;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
        this.teamRating = rating;

    }

    public Long getTeamid() {
        return teamid;
    }


    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
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
        return teamRating;
    }

    public void setTeamRating(int teamRating) {
        this.teamRating = teamRating;
    }
    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }


    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }
}
