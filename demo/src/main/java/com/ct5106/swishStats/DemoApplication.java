package com.ct5106.swishStats;

import com.ct5106.swishStats.domain.League;
import com.ct5106.swishStats.domain.Team;
import com.ct5106.swishStats.repository.TeamRepository;
import com.ct5106.swishStats.repository.LeagueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner
{
	private final LeagueRepository leagueRepository;
	private final TeamRepository teamRepository;
	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

	public DemoApplication(LeagueRepository leagueRepository, TeamRepository teamRepository)
	{
		this.leagueRepository = leagueRepository;
		this.teamRepository = teamRepository;
	}


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		logger.info("Application started");
	}

	@Override
	public void run(String... args) throws Exception
	{
		League PremierLeague = new League("Premier League");
		League Championship = new League("Championship");
		leagueRepository.save(PremierLeague);
		leagueRepository.save(Championship);

		Team manchesterCity = new Team("Manchester City", 2, 4,2, 0, 2101);
		manchesterCity.setConference(PremierLeague);
		teamRepository.save(manchesterCity);

		Team watford = new Team("Watford", 7, 4,1, 2, 1413);
		watford.setConference(Championship);
		teamRepository.save(watford);

//Fetch both conferences and log to console
		for (League league : leagueRepository.findAll())
		{
			logger.info("name: {}", league.getName());
		}

		for (Team team : teamRepository.findAll()) {
			logger.info("Team: {}, League: {}, Standing: {}, Wins: {}, Losses: {}, Rating: {}", team.getTeamname(), team.getConference().getName(), team.getStanding(), team.getWins(), team.getLosses(), team.getTeamRating());
		}
	}
}
