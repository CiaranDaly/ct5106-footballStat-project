package com.ct5106.swishStats.web;

import com.ct5106.swishStats.domain.Team;
import com.ct5106.swishStats.repository.TeamRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ct5106.swishStats.domain.Conference;
import com.ct5106.swishStats.repository.ConferenceRepository;

@RestController
public class ConferenceController {
    private final ConferenceRepository conferenceRepository;
    private final TeamRepository teamRepository;

    public ConferenceController(ConferenceRepository conferenceRepository, TeamRepository teamRepository) {
        this.conferenceRepository = conferenceRepository;
        this.teamRepository = teamRepository;
    }

    @GetMapping("/conferences")
    public Iterable<Conference> getAllConferences() {
        return conferenceRepository.findAll();
    }

    @GetMapping("/teams")
    public Iterable<Team> getAllTeams() {
        return teamRepository.findAll();
    }
}
