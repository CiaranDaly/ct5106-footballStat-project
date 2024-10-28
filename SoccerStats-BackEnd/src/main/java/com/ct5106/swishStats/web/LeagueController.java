package com.ct5106.swishStats.web;

import com.ct5106.swishStats.domain.Team;
import com.ct5106.swishStats.repository.TeamRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ct5106.swishStats.domain.League;
import com.ct5106.swishStats.repository.LeagueRepository;

@RestController
public class LeagueController {
    private final LeagueRepository leagueRepository;
    private final TeamRepository teamRepository;

    public LeagueController(LeagueRepository leagueRepository, TeamRepository teamRepository) {
        this.leagueRepository = leagueRepository;
        this.teamRepository = teamRepository;
    }

    @GetMapping("/leagues")
    public Iterable<League> getAllConferences() {
        return leagueRepository.findAll();
    }

    @GetMapping("/league/teams")
    public Iterable<Team> getAllTeamsForLeague() { //list all teams that are in a league
        return teamRepository.findAll();
    }
}
