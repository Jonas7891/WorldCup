package com.worldcup.worldcup.modules.login.entity;

import com.worldcup.worldcup.shared.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseEntity {
    @Column (length = 15, unique = true, nullable = false)
    private String name;

    private String description;
}