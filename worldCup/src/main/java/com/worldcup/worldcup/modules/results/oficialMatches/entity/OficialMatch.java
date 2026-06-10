package com.worldcup.worldcup.modules.results.oficialMatches.entity;

import com.worldcup.worldcup.modules.mundial.phase.entity.Phase;
import com.worldcup.worldcup.modules.mundial.stadium.entity.Stadium;
import com.worldcup.worldcup.modules.results.oficialMatches.enums.OficialMatchStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "official_match")
@Table(name = "official_match")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OficialMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_official_match", nullable = false)
    private Integer id_official_match;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_phase", nullable = false)
    private Phase phase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_stadium", nullable = false)
    private Stadium stadium;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OficialMatchStatus status;
}
