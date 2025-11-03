package com.example.baitap.repository;


import com.example.baitap.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPlayerRepository extends JpaRepository<Player,Integer> {

}
