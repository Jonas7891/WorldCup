package com.worldcup.worldcup.modules.simulations.match.entity;

import com.worldcup.worldcup.modules.mundial.phase.entity.Phase;
import com.worldcup.worldcup.modules.mundial.stadium.entity.Stadium;
import com.worldcup.worldcup.modules.simulations.simulation.entity.Simulation;
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

    @ManyToOne
    @JoinColumn(name = "id_simulation", nullable = false)
    private Simulation simulation;

    @ManyToOne
    @JoinColumn(name = "id_phase", nullable = false)
    private Phase phase;

    @ManyToOne
    @JoinColumn(name = "id_stadium", nullable = false)
    private Stadium stadium;

    @Column(name = "match_date", nullable = false)
    private LocalDateTime match_date;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EnumMatch status;
}