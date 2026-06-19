package com.worldcup.modules.results.controller;

import com.worldcup.modules.results.dto.request.OficialMatchCreateRequest;
import com.worldcup.modules.results.dto.response.OficialMatchResponse;
import com.worldcup.modules.results.service.interfaces.OficialMatchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/oficial-matches")
public class OficialMatchController {
    private final OficialMatchService oficialMatchService;

    @GetMapping
    public ResponseEntity<List<OficialMatchResponse>> getAllMatches(){
        List<OficialMatchResponse> response = oficialMatchService.getAllMatches();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<OficialMatchResponse> createMatch(@Valid @RequestBody OficialMatchCreateRequest request) {
        OficialMatchResponse response = oficialMatchService.createMatch(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OficialMatchResponse> updateMatch(
            @PathVariable Integer id,
            @Valid @RequestBody OficialMatchCreateRequest request) {
        OficialMatchResponse response = oficialMatchService.updateMatch(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OficialMatchResponse> getMatchById(@PathVariable Integer id) {
        OficialMatchResponse response = oficialMatchService.getMatchById(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Integer id) {
        oficialMatchService.deleteMatch(id);
        return ResponseEntity.noContent().build();
    }
}
