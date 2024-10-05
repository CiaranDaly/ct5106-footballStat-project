package com.ct5106.swishStats.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ct5106.swishStats.domain.Player;
import com.ct5106.swishStats.domain.Team;
import com.ct5106.swishStats.repository.PlayerRepository;
import com.ct5106.swishStats.repository.TeamRepository;

import java.util.List;

@RestController
public class PlayerController {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public PlayerController(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @GetMapping("/player")
    public List<Player> getPlayers(
            @RequestParam(required = false) Integer minRating,
            @RequestParam(required = false) Integer maxRating) {
        if (minRating != null && maxRating != null) {
            return playerRepository.findPlayersByRatingRange(minRating, maxRating);
        } else {
            return (List<Player>) playerRepository.findAll();
        }
    }

    @GetMapping("/player/teams")
    public Iterable<Team> getAllTeamsForPlayer() {
        return teamRepository.findAll(); //get all teams that have players
    }
}