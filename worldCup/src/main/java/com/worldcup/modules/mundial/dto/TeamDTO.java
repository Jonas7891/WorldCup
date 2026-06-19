package com.worldcup.modules.mundial.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TeamDTO {
    private Integer id_team;
    private String name;
    private int id_country;
    private int id_group;
    private Boolean status;
}
