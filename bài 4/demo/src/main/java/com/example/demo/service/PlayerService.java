package com.example.demo.service;

import com.example.demo.entity.Player;
import com.example.demo.repository.IPlayerRepository;
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
         return iPlayerRepository.findById(id);
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
