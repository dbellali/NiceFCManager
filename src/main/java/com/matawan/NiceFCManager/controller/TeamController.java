package com.matawan.NiceFCManager.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matawan.NiceFCManager.dto.TeamCreateDto;
import com.matawan.NiceFCManager.exception.TeamNotFoundException;
import com.matawan.NiceFCManager.service.TeamService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/team")
public class TeamController extends AbstractController {
    
    @Autowired
    private TeamService teamService;

    @GetMapping("")
    public ResponseEntity<?> getAllteams(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "name,asc") String[] sort
    ) {
        ResponseEntity<?> response;
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by(this.getSortParams(sort)));
            response = this.successResponse(teamService.getAllTeams(pageable));
        } catch (IllegalArgumentException e) {
            response = this.errorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (RuntimeException e) {
            System.out.println(e);
            response = this.errorResponse();
        }
        return response;
    }

    @PostMapping("")
    public ResponseEntity<?> createTeam(@RequestBody @Valid TeamCreateDto team) {
        ResponseEntity<?> response;
        try {
            response = this.successResponse(HttpStatus.CREATED, this.teamService.createTeam(team));
        } catch (RuntimeException e) {
            response = this.errorResponse();
        }
        return response;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getTeam(@PathVariable Integer id) {
        ResponseEntity<?> response;
        try {
            response = this.successResponse(this.teamService.getTeam(id));
        } catch (TeamNotFoundException e) {
            response = this.errorResponse(e);
        } catch (RuntimeException e) {
            response = this.errorResponse();
        }
        return response;
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateTeam(@PathVariable Integer id, @RequestBody @Valid TeamCreateDto team) {
        ResponseEntity<?> response;
        try {
            response = this.successResponse(this.teamService.updateTeam(id, team));
        } catch (TeamNotFoundException e) {
            response = this.errorResponse(e);
        } catch (RuntimeException e) {
            response = this.errorResponse();
        }
        return response;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable Integer id) {
        ResponseEntity<?> response;
        try {
            response = this.successResponse(this.teamService.deleteTeam(id));
        } catch (TeamNotFoundException e) {
            response = this.errorResponse(e);
        } catch (RuntimeException e) {
            response = this.errorResponse();
        }
        return response;
    }
}
