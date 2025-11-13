package com.example.baitap.service;

import com.example.baitap.entity.Team;

import java.util.List;

public interface ITeamService {
    List<Team> findAll();
    Team findById(Integer id);
}
