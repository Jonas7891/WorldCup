package com.worldcup.modules.login.entity;

import com.worldcup.shared.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.cdi.Eager;

import java.time.LocalDateTime;

@Entity(name = "credential")
@NoArgsConstructor
@AllArgsConstructor
public class Credential extends BaseEntity {
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="user_id", nullable= false, unique = true)
    private AppUser user;

    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "last_access")
    private LocalDateTime lastAccess;

    @PrePersist
    public void onAccess() {
        lastAccess = LocalDateTime.now();
    }
}
