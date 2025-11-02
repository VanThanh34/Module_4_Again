package com.example.demo.service;

import com.example.demo.entity.Player;

import java.util.List;

public interface IPlayerService {
    List<Player> findAll();

    Player findById(int id);

    void save(Player player);

    void update(Player player);

    void delete(int id);
}
