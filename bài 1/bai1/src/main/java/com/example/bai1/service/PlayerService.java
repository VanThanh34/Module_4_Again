package com.example.bai1.service;

import com.example.bai1.entity.Player;
import com.example.bai1.repository.IPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService implements IPlayerService {
    @Autowired
    private IPlayerRepository iPlayerRepository;

    @Override
    public List<Player> findAll() {
        return iPlayerRepository.findAll();
    }
}
