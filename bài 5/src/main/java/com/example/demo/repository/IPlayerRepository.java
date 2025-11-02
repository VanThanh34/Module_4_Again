package com.example.demo.repository;

import com.example.demo.entity.Player;

import java.util.List;

public interface IPlayerRepository {
    List<Player> findAll();

    Player findById(int id);

    void deleteById(int id);

    void save(Player player);
}
