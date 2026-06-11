package com.worldcup.modules.simulations.match.controller;

import com.worldcup.modules.simulations.match.dto.MatchDTO;
import com.worldcup.modules.simulations.match.entity.Match;
import com.worldcup.modules.simulations.match.services.interfaces.IMatch;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/matches")
public class MatchController {
	private final IMatch service;

	@PostMapping
	public ResponseEntity<String> create(@RequestBody MatchDTO matchDTO) {
		String result = service.Create(matchDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	@GetMapping
	public ResponseEntity<List<Match>> getAll() {
		return ResponseEntity.ok(service.GetAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Match> getById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.GetById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Match> update(@PathVariable Integer id, @RequestBody MatchDTO matchDTO) {
		return ResponseEntity.ok(service.Update(id, matchDTO));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Match> partialUpdate(@PathVariable Integer id, @RequestBody MatchDTO matchDTO) {
		return ResponseEntity.ok(service.PartialUpdate(id, matchDTO));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		boolean deleted = service.Delete(id);
		if (deleted) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@PatchMapping("/{id}/logical-delete")
	public ResponseEntity<String> logicalDelete(@PathVariable Integer id) {
		boolean result = service.LogicalDelete(id);
		if (result) {
			return ResponseEntity.ok("Partido eliminado lógicamente");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo eliminar el partido lógicamente");
	}
}
