package com.worldcup.worldcup.modules.results.entity;


import com.worldcup.worldcup.modules.results.enums.OficialMatchStatus;
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

/**    @ManyToOne
    @JoinColumn(name = "id_phase")
    private Phase phase;

    @ManyToOne
    @JoinColumn(name = "id_stadium")
    private Stadium stadium;**/

    @Column(nullable = false)
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private OficialMatchStatus oficialMatchStatus;





}
