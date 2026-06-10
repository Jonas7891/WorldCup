package com.worldcup.worldcup.modules.mundial.team.entity;

import com.worldcup.worldcup.modules.mundial.country.entity.Country;
import com.worldcup.worldcup.modules.simulations.group.entity.Group;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="team")
@Table(name="team")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Team {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_team", nullable = false)
    private Integer id_team;

    @Column(name="name", length = 100, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_country", nullable = false)
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_group", nullable = false)
    private Group group;

    @Column(name="status", nullable = false)
    private Boolean status;
}
