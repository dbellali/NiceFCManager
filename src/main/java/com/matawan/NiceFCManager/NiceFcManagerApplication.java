package com.matawan.NiceFCManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.matawan.NiceFCManager.dto.PlayerCreateDto;
import com.matawan.NiceFCManager.dto.TeamCreateDto;
import com.matawan.NiceFCManager.enumeration.PlayerPosition;
import com.matawan.NiceFCManager.model.Team;
import com.matawan.NiceFCManager.service.PlayerService;
import com.matawan.NiceFCManager.service.TeamService;

@SpringBootApplication
public class NiceFcManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NiceFcManagerApplication.class, args);
	}

	@Bean
	CommandLineRunner run(TeamService teamService, PlayerService playerService) {
		return args -> {
			this.createTeams(teamService);
			this.createPlayers(playerService);
		};
	}

	private void createTeams(TeamService teamService) {
		for (int i = 1; i <= 5; i++) {
			TeamCreateDto team = new TeamCreateDto(
				"team " + i,
				"T" + i,
				100 * i,
				new ArrayList<PlayerCreateDto>()
			);
			teamService.createTeam(team);
		}
	}

	private void createPlayers(PlayerService playerService) {
		Random random = new Random();
		List<PlayerPosition> position = new ArrayList<>(List.of(PlayerPosition.ATTAQUANT));
		for (int i = 1; i <= 10; i++) {
			Integer teamId = random.nextInt(5) + 1;
			PlayerCreateDto player = new PlayerCreateDto(
				"player " + i + " team " + teamId,
				position
			);
			playerService.addPlayer(teamId, player);
		}
	}

}
