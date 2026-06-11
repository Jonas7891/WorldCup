package com.worldcup.modules.results.oficialMatchParticipants.controller;

import com.worldcup.modules.results.oficialMatchParticipants.dto.request.OficialMatchParticipantsCreateRequest;
import com.worldcup.modules.results.oficialMatchParticipants.dto.response.OficialMatchParticipantsResponse;
import com.worldcup.modules.results.oficialMatchParticipants.service.OficalMatchParticipantsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/match-participants")
@RequiredArgsConstructor
public class OficialMatchParticpantsController {
    private final OficalMatchParticipantsService participantsService;

    @GetMapping
    public ResponseEntity<List<OficialMatchParticipantsResponse>> getAllMatchParticipants() {
        List<OficialMatchParticipantsResponse> response = participantsService.getAllMatchParticpants();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OficialMatchParticipantsResponse> getMatchParticipantsById(@PathVariable Integer id) {
        OficialMatchParticipantsResponse response = participantsService.getMatchParticipantsById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<OficialMatchParticipantsResponse> createMatchParticipants(
            @Valid @RequestBody OficialMatchParticipantsCreateRequest request) {
        OficialMatchParticipantsResponse response = participantsService.createMatchParticpants(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OficialMatchParticipantsResponse> updateMatchParticipants(
            @PathVariable Integer id,
            @Valid @RequestBody OficialMatchParticipantsCreateRequest request) {
        OficialMatchParticipantsResponse response = participantsService.updateMatchParticipants(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatchParticipants(@PathVariable Integer id) {
        participantsService.deleteMatchParticipants(id);
        return ResponseEntity.noContent().build();
    }
}
