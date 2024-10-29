package com.ct5106.swishStats;

import com.ct5106.swishStats.domain.League;
import com.ct5106.swishStats.domain.Manager;
import com.ct5106.swishStats.domain.Player;
import com.ct5106.swishStats.domain.Team;
import com.ct5106.swishStats.repository.TeamRepository;
import com.ct5106.swishStats.repository.LeagueRepository;
import com.ct5106.swishStats.repository.ManagerRepository;
import com.ct5106.swishStats.repository.PlayerRepository;

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
	private final PlayerRepository playerRepository;
	private final ManagerRepository managerRepository;
	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

	public DemoApplication(LeagueRepository leagueRepository, TeamRepository teamRepository, PlayerRepository playerRepository, ManagerRepository managerRepository)
	{
		this.leagueRepository = leagueRepository;
		this.teamRepository = teamRepository;
		this.playerRepository = playerRepository;
		this.managerRepository = managerRepository;
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
		League MLS = new League("Major League Soccer");
		League bundesliga = new League("Bundesliga");
		League serieA= new League("Serie A");
		League SPL = new League("Scottish Premier League");
		leagueRepository.save(PremierLeague);
		leagueRepository.save(Championship);
		leagueRepository.save(MLS);
		leagueRepository.save(bundesliga);
		leagueRepository.save(serieA);
		leagueRepository.save(SPL);

		Team manchesterCity = new Team("Manchester City", 2, 4, 2, 0, 2101);
		manchesterCity.setLeague(PremierLeague);
		teamRepository.save(manchesterCity);

		Team watford = new Team("Watford", 7, 4, 1, 2, 1413);
		watford.setLeague(Championship);
		teamRepository.save(watford);
		
		Team interMiami = new Team("Inter Miami", 1, 19, 8, 4, 1806);
		interMiami.setLeague(MLS);
		teamRepository.save(interMiami);
		
		Team laGalaxy = new Team("LA Galaxy", 5, 11, 9, 9, 1783);
		laGalaxy.setLeague(MLS);
		teamRepository.save(laGalaxy);
		
		Team liverpool = new Team("Liverpool", 3, 4, 3, 1, 2096);
		liverpool.setLeague(PremierLeague);
		teamRepository.save(liverpool);
		
		Team manU = new Team("Manchester United", 6, 1, 3, 4, 1806);
		manU.setLeague(PremierLeague);
		teamRepository.save(manU);
		
		Team arsenal = new Team("Arsenal", 8, 19, 8, 4, 1806);
		arsenal.setLeague(PremierLeague);
		teamRepository.save(arsenal);
		
		Team tottenham = new Team("Tottenham", 9, 19, 8, 4, 1806);
		tottenham.setLeague(PremierLeague);
		teamRepository.save(tottenham);

		Team celtic = new Team("Celtic", 1, 100, 0, 0, 9999);
		celtic.setLeague(SPL);
		teamRepository.save(celtic);
		

		
		
		Player haaland = new Player("Erling", "Haaland", "Striker", 24, 91);
		haaland.setTeam(manchesterCity);
		playerRepository.save(haaland);
		
		
		Player messi = new Player("Lionel", "Messi", "Centre Forward", 35, 88);
		messi.setTeam(interMiami);
		playerRepository.save(messi);

		Player samaras = new Player("Georgios", "Samaras", "Forward", 39, 77);
		samaras.setTeam(celtic);
		playerRepository.save(samaras);
		
		Manager pepGuardiola = new Manager("Pep", "Guardiola", "Spain", 94);
		pepGuardiola.setTeam(manchesterCity);
		managerRepository.save(pepGuardiola);

		Manager gerardoMartino = new Manager("Gerardo", "Martino", "Argentina", 79);
		gerardoMartino.setTeam(interMiami);
		managerRepository.save(gerardoMartino);
		

//Fetch both conferences and log to console
		for (League league : leagueRepository.findAll())
		{
			logger.info("name: {}", league.getName());
		}

		for (Team team : teamRepository.findAll()) {
			logger.info("Team: {}, League: {}, Standing: {}, Wins: {}, Losses: {}, Rating: {}", team.getTeamName(), team.getLeague().getName(), team.getStanding(), team.getWins(), team.getLosses(), team.getTeamRating());
		}
		
		for (Player player : playerRepository.findAll()) {
			logger.info("first name: {}, last name: {}, position: {}, age: {}, team: {}, Rating: {}", player.getFirstName(), player.getLastName(), player.getPosition(), player.getAge(), player.getTeam().getTeamName(), player.getPlayerRating());
		}
		
		for (Manager manager : managerRepository.findAll()) {
			logger.info("first name: {}, last name: {}, nationality: {}, team: {}, manager rating: {}", manager.getFirstName(), manager.getLastName(), manager.getNationality(), manager.getTeam().getTeamName(), manager.getManagerRating());
		}
	}
}
