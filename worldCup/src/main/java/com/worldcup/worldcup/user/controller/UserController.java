package com.worldcup.worldcup.user.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.worldcup.worldcup.user.entity.AppUser;
import com.worldcup.worldcup.user.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public AppUser create(@RequestBody AppUser user) {
        return service.create(user);
    }

    @GetMapping
    public List<AppUser> getAll() {
        return service.findAll();
    }
}

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<AppUser> updateUser(
            @PathVariable Long id,
            @RequestBody AppUser userDetails) {

        return repository.findById(id)
                .map(user -> {

                    user.setUsername(userDetails.getUsername());

                    user.setPassword(userDetails.getPassword());

                    user.setRole(userDetails.getRole());

                    return ResponseEntity.ok(
                            repository.save(user)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        return repository.findById(id)
                .map(user -> {

                    repository.delete(user);

                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}