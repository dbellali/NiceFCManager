package com.matawan.NiceFCManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matawan.NiceFCManager.model.PlayerEntity;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Integer>{
    
}
