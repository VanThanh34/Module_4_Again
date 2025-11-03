package com.example.baitap.service;


import com.example.baitap.entity.Player;

import java.util.List;

public interface IPlayerService {
    List<Player> findAll();

    Player findById(int id);

    void deleteById(int id);

    void save(Player player);
}
