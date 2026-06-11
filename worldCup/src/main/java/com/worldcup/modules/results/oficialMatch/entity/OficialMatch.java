package com.worldcup.modules.results.oficialMatch.entity;


import com.worldcup.modules.mundial.phase.entity.Phase;
import com.worldcup.modules.mundial.stadium.entity.Stadium;
import com.worldcup.modules.results.oficialMatch.enums.OficialMatchStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "OficialMatch")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class OficialMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_oficialMatch",nullable = false)
    private Integer id_oficialMatch;

  @ManyToOne
    @JoinColumn(name = "id_phase")
    private Phase phase;

    @ManyToOne
    @JoinColumn(name = "id_stadium")
    private Stadium stadium;

    @Column(nullable = false)
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private OficialMatchStatus oficialMatchStatus;





}
