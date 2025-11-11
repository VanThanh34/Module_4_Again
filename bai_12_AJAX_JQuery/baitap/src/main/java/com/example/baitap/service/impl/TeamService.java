package com.example.baitap.service.impl;

import com.example.baitap.entity.Team;
import com.example.baitap.repository.ITeamRepository;
import com.example.baitap.service.ITeamService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeamService implements ITeamService {
    private final ITeamRepository iTeamRepository;

    public TeamService(ITeamRepository iTeamRepository) {
        this.iTeamRepository = iTeamRepository;
    }

    @Override
    public List<Team> findAll() {
        return iTeamRepository.findAll();
    }

    @Override
    public Team findById(Integer id) {
        return iTeamRepository.findById(id).orElse(null);
    }
}
