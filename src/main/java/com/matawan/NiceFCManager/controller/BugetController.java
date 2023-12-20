package com.matawan.NiceFCManager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buget")
public class BugetController {
    

    @GetMapping("")
    public String getTotalBuget() {
        return "i have 1000";
    }

    @GetMapping("{id}")
    public String getTeamBuget() {
        return "paris have 10 euros";
    }

    
}
