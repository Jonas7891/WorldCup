package com.worldcup.modules.mundial.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CountryDTO {
    private Integer id_country;
    private String name_country;
    private String fifa_code;
    private String continent;
    private Integer ranking_fifa;
    private Boolean status;
}
