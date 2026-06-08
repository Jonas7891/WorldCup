package com.worldcup.worldcup.modules.simulations.group.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="group")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Group {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_group", nullable = false)
    private Integer id_group;

    @Column(name="name", length = 1, nullable = false)
    private String name;

    @Column(name="id_simulation", nullable = false)
    private int id_simulation;

    @Column(name="status", nullable = false)
    private Boolean status;
}
