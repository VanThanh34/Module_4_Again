package com.example.baitap.service;


import com.example.baitap.entity.Player;
import com.example.baitap.repository.IPlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService implements IPlayerService {

    private final IPlayerRepository iPlayerRepository;

    public PlayerService(IPlayerRepository iPlayerRepository) {
        this.iPlayerRepository = iPlayerRepository;
    }

    @Override
    public List<Player> findAll() {
        return iPlayerRepository.findAll();
    }

    @Override
    public Player findById(int id) {
         return iPlayerRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(int id) {
        iPlayerRepository.deleteById(id);
    }

    @Override
    public void save(Player player) {
        iPlayerRepository.save(player);
    }
}
