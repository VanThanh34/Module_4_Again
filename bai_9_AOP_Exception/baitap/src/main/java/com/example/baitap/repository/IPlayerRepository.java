package com.example.baitap.repository;


import com.example.baitap.entity.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IPlayerRepository extends JpaRepository<Player,Integer> {
    @Query("""
        SELECT p FROM Player p
        WHERE (:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')))
          AND (:startDate IS NULL OR p.dob >= :startDate)
          AND (:endDate IS NULL OR p.dob <= :endDate)
    """)
    Page<Player> searchPlayers(
            @Param("name") String name,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            Pageable pageable
    );

    long countByStatus(String status);

    List<Player> findByStatus(String status);
}
