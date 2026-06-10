package com.worldcup.worldcup.modules.results.participantOfficialParty.entity;

import com.worldcup.worldcup.modules.mundial.team.entity.Team;
import com.worldcup.worldcup.modules.results.oficialMatches.entity.OficialMatch;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "participant_official_party")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantOfficialParty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_participant_official_party", nullable = false)
    private Integer id_participant_official_party;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_official_match", nullable = false)
    private OficialMatch official_match;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_team", nullable = false)
    private Team team;

    @Column(name = "goals", nullable = false)
    private Integer goals;

    @Enumerated(EnumType.STRING)
    @Column(name = "result")
    private EnumOfficialPartyResult result;
}

