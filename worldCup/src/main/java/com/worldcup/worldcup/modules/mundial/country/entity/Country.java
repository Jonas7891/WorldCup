package com.worldcup.worldcup.modules.mundial.country.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="country")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Country {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_country", nullable = false)
    private Integer id_country;

    @Column(name="name_country",length = 100, nullable = false, unique = true)
    private String name_country;

    @Column(name="ranking_fifa", nullable = false, unique = true)
    private Integer ranking_fifa;

    @Column(name="continent", length = 50, nullable = false)
    private String continent;

    @Column(name="status", nullable = false)
    private Boolean status;
}
