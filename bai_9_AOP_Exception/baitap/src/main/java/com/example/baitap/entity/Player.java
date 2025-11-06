package com.example.baitap.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "players")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "experience", length = 50)
    private String experience;

    @Column(name = "position", length = 50)
    private String position;

    @Column(name = "avatar", length = 250)
    private String avatar;

    @Column(nullable = false)
    private String status = "Dự bị";
    
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}