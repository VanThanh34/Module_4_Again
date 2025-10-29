package com.example.bai1.repository;

import com.example.bai1.entity.Player;

import java.util.List;

public interface IPlayerRepository {
    List<Player> findAll();

    Player findById(int id);

    void deleteById(int id);

    void save(Player player);
}
