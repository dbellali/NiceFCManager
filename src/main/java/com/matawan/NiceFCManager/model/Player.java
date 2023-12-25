package com.matawan.NiceFCManager.model;

import java.util.Arrays;
import java.util.List;

import com.matawan.NiceFCManager.enumeration.PlayerPosition;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "player")
public class Player {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;
    
    @ElementCollection(targetClass = PlayerPosition.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "player_position", joinColumns = @JoinColumn(name = "player_id"))
    private List<PlayerPosition> position;
   
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
    public List<PlayerPosition> getPosition() {
        return position;
    }

    public void setPosition(List<PlayerPosition> position) {
        this.position = position;
    }

    public void addPosition(PlayerPosition ...position) {
        this.position.addAll(Arrays.asList(position));
    }

    public void addPosition(List<PlayerPosition> position) {
        this.position.addAll(position);
    }

}
