package com.example.baitap.service.impl;


import com.example.baitap.entity.Player;
import com.example.baitap.repository.IPlayerRepository;
import com.example.baitap.service.IPlayerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    @Override
    public Page<Player> findAllPageable(int page, int size) {
        Sort sort = Sort.by("name").ascending()
                .and(Sort.by("dob").ascending());

        Pageable pageable = PageRequest.of(page, size, sort);

        return iPlayerRepository.findAll(pageable);
    }

    @Override
    public Page<Player> searchPlayers(String name, LocalDate startDate, LocalDate endDate, int page, int size) {
        Sort sort = Sort.by("name").ascending().and(Sort.by("dob").descending());
        Pageable pageable = PageRequest.of(page, size, sort);
        return iPlayerRepository.searchPlayers(name, startDate, endDate, pageable);
    }

}
