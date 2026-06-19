package com.worldcup.modules.mundial.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="country")
@Table(name="country")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Country {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_country", nullable = false)
    private Integer id_country;

    @Column(name="name_country", length = 100, nullable = false, unique = true)
    private String name_country;

    @Column(name="fifa_code", length = 3, nullable = false, unique = true)
    private String fifa_code;

    @Column(name="continent", length = 50, nullable = false)
    private String continent;

    @Column(name="ranking_fifa", nullable = true)
    private Integer ranking_fifa;

    @Column(name="status", nullable = false)
    private Boolean status;
}
