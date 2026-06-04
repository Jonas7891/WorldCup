package com.worldcup.worldcup.modules.user.entity;

import com.worldcup.worldcup.modules.role.entity.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUser{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=70, nullable = false)
    private String firstName;

    @Column(length=70, nullable = false)
    private String lastName;

    @Column (length=70, nullable = false)
    private String mail;

    private boolean status;
    private LocalDateTime register;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Credential credential;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
}