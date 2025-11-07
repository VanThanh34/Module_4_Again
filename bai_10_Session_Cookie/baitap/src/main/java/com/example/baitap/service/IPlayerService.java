package com.example.baitap.service;


import com.example.baitap.entity.Player;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface IPlayerService {
    List<Player> findAll();

    Player findById(int id);

    void deleteById(int id);

    void save(Player player);

    Page<Player> findAllPageable(int page, int size);
    Page<Player> searchPlayers(String name, LocalDate startDate, LocalDate endDate, int page, int size);

    String toggleStatus(Integer id);

    List<Player> findRegisteredPlayers();

    List<Player> findFavorites();
}
