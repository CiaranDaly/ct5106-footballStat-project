package com.ct5106.swishStats.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playerId;

    private String firstName;
    private String lastName;
    private String position;
    private int age;
    private int playerRating;
    

    @ManyToOne
    @JoinColumn(name = "team_id")
    @JsonIgnoreProperties({"players", "manager"})
    private Team team;

    public Player() {
    }

    public Player(String firstName, String lastName, String position, int age, int playerRating) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.age = age;
        this.playerRating = playerRating;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public int getPlayerRating() {
        return playerRating;
    }
    public void setPlayerRating(int playerRating) {
        this.playerRating = playerRating;
    }

    public Team getTeam() {
        return team;
    }
    public void setTeam(Team team) {
        this.team = team;
    }
}
