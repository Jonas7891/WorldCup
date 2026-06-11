package com.worldcup.modules.mundial.stadium.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="stadium")
@Table(name="stadium")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Stadium {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_stadium", nullable = false)
    private Integer id_stadium;

    @Column(name="name", length = 100, nullable = false)
    private String name;

    @Column(name="city", length = 100, nullable = false)
    private String city;

    @Column(name="capacity", nullable = false)
    private Integer capacity;

    @Column(name="status", nullable = false)
    private Boolean status;
}
