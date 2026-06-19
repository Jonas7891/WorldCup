package com.worldcup.modules.simulations.controller;

import com.worldcup.modules.simulations.dto.SimulationDTO;
import com.worldcup.modules.simulations.entity.Simulation;
import com.worldcup.modules.simulations.service.interfaces.ISimulation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/simulation")
public class SimulationController {
	private final ISimulation service;

	@PostMapping
	public ResponseEntity<String> create(@RequestBody SimulationDTO simulationDTO) {
		String result = service.Create(simulationDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	@GetMapping
	public ResponseEntity<List<Simulation>> getAll() {
		return ResponseEntity.ok(service.GetAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Simulation> getById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.GetById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Simulation> update(@PathVariable Integer id, @RequestBody SimulationDTO simulationDTO) {
		return ResponseEntity.ok(service.Update(id, simulationDTO));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Simulation> partialUpdate(@PathVariable Integer id, @RequestBody SimulationDTO simulationDTO) {
		return ResponseEntity.ok(service.PartialUpdate(id, simulationDTO));
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
			return ResponseEntity.ok("Grupo eliminado lógicamente");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo eliminar el grupo lógicamente");
	}
}
