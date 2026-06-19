package com.worldcup.modules.results.entity;


import com.worldcup.modules.mundial.entity.Phase;
import com.worldcup.modules.mundial.entity.Stadium;
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
