package com.worldcup.modules.results.entity;

import com.worldcup.modules.mundial.entity.Team;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "OficialMatchParticipants")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OficialMatchParticipants {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_oficialMatchParticipants", nullable = false)
    private Integer id_oficialMatchParticipants;

    @ManyToOne
    @JoinColumn(name = "id_oficialMatch")
    private OficialMatch oficialMatch;

    @ManyToOne
    @JoinColumn(name = "id_team")
    private Team team;

    @Column(name = "goals", nullable = false)
    private Integer goals;

    @Enumerated(EnumType.STRING)
    private MatchResultStatus matchResultStatus;

}
