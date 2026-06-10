package com.worldcup.worldCup.modules.simulations.participatingParty.entity;

import com.worldcup.worldCup.modules.mundial.team.entity.Team;
import com.worldcup.worldCup.modules.simulations.match.entity.Match;
import com.worldcup.worldCup.modules.simulations.participatingParty.entity.EnumParticipatingPartyResult;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "participating_party")
@Table(name = "participating_party")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipatingParty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_participating_party", nullable = false)
    private Integer id_participating_party;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_match", nullable = false)
    private Match match;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_team", nullable = false)
    private Team team;

    @Column(name = "goals", nullable = false)
    private Integer goals;

    @Enumerated(EnumType.STRING)
    @Column(name = "result")
    private EnumParticipatingPartyResult result;
}
