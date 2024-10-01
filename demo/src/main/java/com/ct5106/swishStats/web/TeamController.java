package com.ct5106.swishStats.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ct5106.swishStats.domain.League;
import com.ct5106.swishStats.domain.Team;
import com.ct5106.swishStats.domain.Player;
import com.ct5106.swishStats.repository.LeagueRepository;
import com.ct5106.swishStats.repository.TeamRepository;
import com.ct5106.swishStats.repository.PlayerRepository;

@RestController
public class TeamController {
    private final LeagueRepository leagueRepository;
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    public TeamController(LeagueRepository leagueRepository, TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.leagueRepository = leagueRepository;
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
        
    }

    @GetMapping("/teams")
    public Iterable<Team> getAllTeams() {
        return teamRepository.findAll();
    }
    
    @GetMapping("/team/players")
    public Iterable<Player> getAllPlayersForTeam() { //get all players that have a team
        return playerRepository.findAll();
    }
    
    @GetMapping("/team/league")
    public Iterable<League> getAllLeaguesForTeam() { //get all leaves that have a team in them
        return leagueRepository.findAll();
    }
}