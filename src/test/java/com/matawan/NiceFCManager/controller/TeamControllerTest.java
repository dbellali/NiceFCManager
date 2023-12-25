package com.matawan.NiceFCManager.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.nullValue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.matawan.NiceFCManager.dto.PlayerCreateDto;
import com.matawan.NiceFCManager.dto.TeamCreateDto;
import com.matawan.NiceFCManager.dto.TeamResponseDto;
import com.matawan.NiceFCManager.enumeration.PlayerPosition;
import com.matawan.NiceFCManager.model.Player;
import com.matawan.NiceFCManager.service.TeamService;


@WebMvcTest(value = TeamController.class)
public class TeamControllerTest {
    private static final String END_POINT_PATH = "/team";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeamService teamService;

    @Test
    public void createTeamWithoutPlayersSuccessTest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        TeamCreateDto teamCreate = new TeamCreateDto("Lille", "FCL", 100000.00, null);
        TeamResponseDto teamResponseDto = new TeamResponseDto(1, "Lille", "FCL", 100000.00, null);
        Mockito.when(teamService.createTeam(teamCreate)).thenReturn(teamResponseDto);
        mockMvc.perform(post(END_POINT_PATH)
            .accept(MediaType.APPLICATION_JSON)
            .content(ow.writeValueAsString(teamCreate))
            .contentType(MediaType.APPLICATION_JSON))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.name").value("Lille"))
        .andExpect(jsonPath("$.acronym").value("FCL"))
        .andExpect(jsonPath("$.budget").value(100000.0))
        .andExpect(jsonPath("$.players", nullValue()));
    }

    // @Test
    public void createTeamWithPlayersSuccessTest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        List<PlayerPosition> postions = Arrays.asList(
            PlayerPosition.ARRIERE_CENTRAL,
            PlayerPosition.MILIEU_DE_TERRAIN_CENTRAL
        );
        List<Player> players = Arrays.asList(
            new Player(1, "Lionel Messi", postions),
            new Player(2, "Cristiano Ronaldo", postions),
            new Player(3, "Neymar Jr.", postions)
        );
        List<PlayerCreateDto> playersDto = Arrays.asList(
            new PlayerCreateDto("Lionel Messi", postions),
            new PlayerCreateDto("Cristiano Ronaldo", postions),
            new PlayerCreateDto("Neymar Jr.", postions)
        );
        TeamCreateDto teamCreate = new TeamCreateDto("Lille", "FCL", 100000.00, playersDto);
        TeamResponseDto teamResponseDto = new TeamResponseDto(1, "Lille", "FCL", 100000.00, players);
        Mockito.when(teamService.createTeam(teamCreate)).thenReturn(teamResponseDto);
        mockMvc.perform(post(END_POINT_PATH)
            .accept(MediaType.APPLICATION_JSON)
            .content(ow.writeValueAsString(teamCreate))
            .contentType(MediaType.APPLICATION_JSON))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.name").value("Lille"))
        .andExpect(jsonPath("$.acronym").value("FCL"))
        .andExpect(jsonPath("$.budget").value(100000.0))
        .andExpect(jsonPath("$.players").isArray())
        .andExpect(jsonPath("$.players.length()").value(3))
        .andExpect(jsonPath("$.players[0].id").value(1))
        .andExpect(jsonPath("$.players[1].id").value(2))
        .andExpect(jsonPath("$.players[2].id").value(3));
    }
}
