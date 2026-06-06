package com.worldcup.worldcup.modules.mundial.phase.controller;

import com.worldcup.worldcup.modules.mundial.phase.dto.PhaseDTO;
import com.worldcup.worldcup.modules.mundial.phase.entity.Phase;
import com.worldcup.worldcup.modules.mundial.phase.services.interfaces.IPhase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/phases")
public class PhaseController {
	private final IPhase service;

	@PostMapping
	public ResponseEntity<String> create(@RequestBody PhaseDTO phaseDTO) {
		String result = service.Create(phaseDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	@GetMapping
	public ResponseEntity<List<Phase>> getAll() {
		return ResponseEntity.ok(service.GetAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Phase> getById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.GetById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Phase> update(@PathVariable Integer id, @RequestBody PhaseDTO phaseDTO) {
		return ResponseEntity.ok(service.Update(id, phaseDTO));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Phase> partialUpdate(@PathVariable Integer id, @RequestBody PhaseDTO phaseDTO) {
		return ResponseEntity.ok(service.PartialUpdate(id, phaseDTO));
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
			return ResponseEntity.ok("Fase eliminada lógicamente");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo eliminar la fase lógicamente");
	}
}
