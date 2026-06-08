package com.worldcup.worldcup.modules.simulations.match.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "match")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_match", nullable = false)
    private int id_match;

    @Column(name = "id_simulation", nullable = false)
    private int id_simulation;

    @Column(name = "id_phase", nullable = false)
    private int id_phase;

    @Column(name = "id_stadium", nullable = false)
    private int id_stadium;

    @Column(name = "match_date", nullable = false)
    private LocalDateTime match_date;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EnumMatch status;
}