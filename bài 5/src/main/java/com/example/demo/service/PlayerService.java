package com.example.demo.service;

import com.example.demo.entity.Player;
import com.example.demo.repository.PlayerRepository;

import java.util.List;

public class PlayerService implements IPlayerService {

    private final PlayerRepository playerRepository = new PlayerRepository();

    @Override
    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public Player findById(int id) {
        return playerRepository.findById(id);
    }

    @Override
    public void save(Player player) {
        playerRepository.save(player);
    }

    @Override
    public void update(Player player) {
        playerRepository.update(player);
    }

    @Override
    public void delete(int id) {
        playerRepository.delete(id);
    }
}
