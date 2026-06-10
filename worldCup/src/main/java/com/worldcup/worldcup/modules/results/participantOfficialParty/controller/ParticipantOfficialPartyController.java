package com.worldcup.worldcup.modules.results.participantOfficialParty.controller;

import com.worldcup.worldcup.modules.results.participantOfficialParty.dto.ParticipantOfficialPartyDTO;
import com.worldcup.worldcup.modules.results.participantOfficialParty.entity.ParticipantOfficialParty;
import com.worldcup.worldcup.modules.results.participantOfficialParty.mapper.ParticipantOfficialPartyMapper;
import com.worldcup.worldcup.modules.results.participantOfficialParty.services.interfaces.IParticipantOfficialParty;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api/participant-official-parties")
public class ParticipantOfficialPartyController {

    private final IParticipantOfficialParty service;
    private final ParticipantOfficialPartyMapper mapper;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody ParticipantOfficialPartyDTO dto) {
        String result = service.Create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    public ResponseEntity<List<ParticipantOfficialPartyDTO>> getAll() {
        List<ParticipantOfficialParty> entities = service.GetAll();
        List<ParticipantOfficialPartyDTO> dtos = entities.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipantOfficialPartyDTO> getById(@PathVariable Integer id) {
        ParticipantOfficialParty entity = service.GetById(id);
        return ResponseEntity.ok(mapper.toDTO(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParticipantOfficialPartyDTO> update(@PathVariable Integer id, @RequestBody ParticipantOfficialPartyDTO dto) {
        ParticipantOfficialParty updated = service.Update(id, dto);
        return ResponseEntity.ok(mapper.toDTO(updated));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ParticipantOfficialPartyDTO> partialUpdate(@PathVariable Integer id, @RequestBody ParticipantOfficialPartyDTO dto) {
        ParticipantOfficialParty updated = service.PartialUpdate(id, dto);
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

