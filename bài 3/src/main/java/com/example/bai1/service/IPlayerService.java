package com.example.bai1.service;

import com.example.bai1.entity.Player;

import java.util.List;

public interface IPlayerService {
    List<Player> findAll();

    Player findById(int id);

    void deleteById(int id);

    void save(Player player);
}
