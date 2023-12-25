package com.matawan.NiceFCManager.model;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "team")
public class Team {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(unique = true)
    private String name;

    private String acronym;

    private double budget;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private List<Player> players;

    
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAcronym() {
        return acronym;
    }
    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void addPlayers(List<Player> players) {
        this.players.addAll(players);
    }

    public void addPlayers(Player ...players) {
        this.players.addAll(Arrays.asList(players));
    }

    public Optional<Player> getPlayerById(Integer id) {
        return this.players.stream().filter(player -> player.getId() == id).findFirst();
    }

    public void removePlayerById(Integer id) {
        this.players.removeIf(player -> player.getId() == id);
    }

    public Boolean playerExistsById(Integer id) {
        return this.players.stream().anyMatch(player -> player.getId() == id);
    }

    public double getBudget() {
        return this.budget;
    }
    public void setBudget(double budget) {
        this.budget = budget;
    }
}
