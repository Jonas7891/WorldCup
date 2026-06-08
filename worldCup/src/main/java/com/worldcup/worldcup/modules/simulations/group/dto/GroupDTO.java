package com.worldcup.worldcup.modules.simulations.group.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class GroupDTO {
    private Integer id_group;
    private String name;
    private int id_simulation;
    private Boolean status;
}
