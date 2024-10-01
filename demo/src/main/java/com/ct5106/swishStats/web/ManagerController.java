package com.ct5106.swishStats.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ct5106.swishStats.domain.Manager;
import com.ct5106.swishStats.domain.Team;
import com.ct5106.swishStats.repository.ManagerRepository;
import com.ct5106.swishStats.repository.TeamRepository;

@RestController
public class ManagerController {
    private final ManagerRepository managerRepository;
    private final TeamRepository teamRepository;

    public ManagerController(ManagerRepository managerRepository, TeamRepository teamRepository) {
        this.managerRepository = managerRepository;
        this.teamRepository = teamRepository;
    }

    @GetMapping("/manager")
    public Iterable<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    @GetMapping("/manager/teams")
    public Iterable<Team> getAllTeamsForManager() { //get all teams that have a manager
        return teamRepository.findAll();
    }
}