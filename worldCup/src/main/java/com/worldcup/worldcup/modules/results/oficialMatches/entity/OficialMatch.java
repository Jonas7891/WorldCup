package com.worldcup.worldcup.modules.results.oficialMatches.entity;


import com.worldcup.worldcup.modules.mundial.phase.entity.Phase;
import com.worldcup.worldcup.modules.mundial.stadium.entity.Stadium;
import com.worldcup.worldcup.modules.results.oficialMatches.enums.OficialMatchStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "partidoOfical")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class OficialMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_oficialMatch;

    @ManyToOne
    @JoinColumn(name = "id_phase", nullable = false)
    private Phase phase;

    @ManyToOne
    @JoinColumn(name = "id_stadium", nullable = false)
    private Stadium stadium;

    @Column(nullable = false)
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private OficialMatchStatus oficialMatchStatus;

}
