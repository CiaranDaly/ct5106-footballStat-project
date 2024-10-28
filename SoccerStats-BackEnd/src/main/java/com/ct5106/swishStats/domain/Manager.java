package com.ct5106.swishStats.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String firstName;
    private String lastName;
    private String nationality;
    private int managerRating;

    @OneToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Manager() {
    }

    public Manager(String firstName, String lastName, String nationality, int managerRating) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;        
        this.managerRating = managerRating;
    }

    public Long getId() {
        return Id;
    }


    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationality() {
        return nationality;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getManagerRating() {
        return managerRating;
    }
    public void setPlayerRating(int managerRating) {
        this.managerRating = managerRating;
    }

    public Team getTeam() {
        return team;
    }
    public void setTeam(Team team) {
        this.team = team;
    }
}
