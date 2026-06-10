package com.worldcup.worldCup.modules.mundial.stadium.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class StadiumDTO {
    private Integer id_stadium;
    private String name;
    private String city;
    private Integer capacity;
    private Boolean status;
}
