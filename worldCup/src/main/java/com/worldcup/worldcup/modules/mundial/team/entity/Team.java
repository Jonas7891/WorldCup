package com.worldcup.worldcup.modules.mundial.team.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="team")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Team {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_team", nullable = false)
    private Integer id_team;

    @Column(name="name", length = 100, nullable = false)
    private String name;

    @Column(name="id_country", nullable = false)
    private int id_country;

    @Column(name="id_group", nullable = false)
    private int id_group;

    @Column(name="status", nullable = false)
    private Boolean status;
}
