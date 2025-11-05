package com.example.baitap.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "teams")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    @Id
    private Integer teamId;
    @Column(name = "team_name", nullable = false, length = 100)
    private String teamName;
    @Column(name = "logo", length = 250)
    private String logo;

}
