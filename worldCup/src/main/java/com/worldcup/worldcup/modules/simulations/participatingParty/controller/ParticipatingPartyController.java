package com.worldcup.worldCup.modules.simulations.participatingParty.controller;

import com.worldcup.worldCup.modules.simulations.participatingParty.dto.ParticipatingPartyDTO;
import com.worldcup.worldCup.modules.simulations.participatingParty.entity.ParticipatingParty;
import com.worldcup.worldCup.modules.simulations.participatingParty.mapper.ParticipatingPartyMapper;
import com.worldcup.worldCup.modules.simulations.participatingParty.services.interfaces.IParticipatingParty;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api/participating-parties")
public class ParticipatingPartyController {

    private final IParticipatingParty service;
    private final ParticipatingPartyMapper mapper;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody ParticipatingPartyDTO dto) {
        String result = service.Create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    public ResponseEntity<List<ParticipatingPartyDTO>> getAll() {
        List<ParticipatingParty> participatingParties = service.GetAll();
        List<ParticipatingPartyDTO> dtos = participatingParties.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipatingPartyDTO> getById(@PathVariable Integer id) {
        ParticipatingParty participatingParty = service.GetById(id);
        return ResponseEntity.ok(mapper.toDTO(participatingParty));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParticipatingPartyDTO> update(@PathVariable Integer id, @RequestBody ParticipatingPartyDTO dto) {
        ParticipatingParty updated = service.Update(id, dto);
        return ResponseEntity.ok(mapper.toDTO(updated));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ParticipatingPartyDTO> partialUpdate(@PathVariable Integer id, @RequestBody ParticipatingPartyDTO dto) {
        ParticipatingParty updated = service.PartialUpdate(id, dto);
        return ResponseEntity.ok(mapper.toDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean deleted = service.Delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

