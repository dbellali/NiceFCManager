package com.matawan.NiceFCManager.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.matawan.NiceFCManager.model.Team;

public interface TeamRepository extends JpaRepository<Team, Integer> {
    
}
