package com.matawan.NiceFCManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matawan.NiceFCManager.model.TeamEntity;

public interface TeamRepository extends JpaRepository<TeamEntity, Integer> {
    
}
