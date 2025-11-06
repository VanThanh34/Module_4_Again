package com.example.baitap.service.impl;


import com.example.baitap.entity.Player;
import com.example.baitap.exception.ExcessPlayerException;
import com.example.baitap.repository.IPlayerRepository;
import com.example.baitap.service.IPlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService implements IPlayerService {

    private final IPlayerRepository iPlayerRepository;

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
    @Override
    public String toggleStatus(Integer id) {
        Player player = iPlayerRepository.findById(id).orElseThrow();
        String oldStatus = player.getStatus();
        String newStatus;

        if ("Dự bị".equals(oldStatus)) {
            long countRegistered = iPlayerRepository.countByStatus("Đã đăng ký");
            if (countRegistered >= 11) throw new ExcessPlayerException("Đội hình tối đa 11 người!");
            newStatus = "Đã đăng ký";
        } else {
            newStatus = "Dự bị";
        }

        player.setStatus(newStatus);
        iPlayerRepository.save(player);
        return oldStatus + " -> " + newStatus;
    }


    @Override
    public List<Player> findRegisteredPlayers() {
        return iPlayerRepository.findByStatus("Đã đăng ký");
    }
}
