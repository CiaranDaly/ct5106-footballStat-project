package com.ct5106.swishStats;

import com.ct5106.swishStats.domain.Conference;
import com.ct5106.swishStats.domain.Team;
import com.ct5106.swishStats.repository.TeamRepository;
import com.ct5106.swishStats.repository.ConferenceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner
{
	private final ConferenceRepository conferenceRepository;
	private final TeamRepository teamRepository;
	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

	public DemoApplication(ConferenceRepository conferenceRepository, TeamRepository teamRepository)
	{
		this.conferenceRepository = conferenceRepository;
		this.teamRepository = teamRepository;
	}


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		logger.info("Application started");
	}

	@Override
	public void run(String... args) throws Exception
	{
		Conference west = new Conference("West");
		Conference east = new Conference("East");
		conferenceRepository.save(west);
		conferenceRepository.save(east);

		Team lakers = new Team("Dallas Mavericks", 5, 50, 32, 83);
		lakers.setConference(west);
		teamRepository.save(lakers);

		Team celtics = new Team("Boston Celtics", 1, 64, 18, 85);
		celtics.setConference(east);
		teamRepository.save(celtics);

//Fetch both conferences and log to console
		for (Conference conference : conferenceRepository.findAll())
		{
			logger.info("name: {}", conference.getName());
		}

		for (Team team : teamRepository.findAll()) {
			logger.info("Team: {}, Conference: {}, Standing: {}, Wins: {}, Losses: {}, Rating: {}", team.getTeamname(), team.getConference().getName(), team.getStanding(), team.getWins(), team.getLosses(), team.getTeamRating());
		}
	}
}
