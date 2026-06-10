package com.worldcup.worldcup.modules.simulations.teamSimulation.controller;

import com.worldcup.worldcup.modules.simulations.teamSimulation.dto.TeamSimulationDTO;
import com.worldcup.worldcup.modules.simulations.teamSimulation.entity.TeamSimulation;
import com.worldcup.worldcup.modules.simulations.teamSimulation.mapper.TeamSimulationMapper;
import com.worldcup.worldcup.modules.simulations.teamSimulation.services.interfaces.ITeamSimulation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api/team-simulations")
public class TeamSimulationController {
	private final ITeamSimulation service;
	private final TeamSimulationMapper mapper;

	@PostMapping
	public ResponseEntity<String> create(@RequestBody TeamSimulationDTO teamSimulationDTO) {
		String result = service.Create(teamSimulationDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	@GetMapping
	public ResponseEntity<List<TeamSimulationDTO>> getAll() {
		List<TeamSimulation> teamSimulations = service.GetAll();
		List<TeamSimulationDTO> dtos = teamSimulations.stream()
				.map(mapper::toDTO)
				.collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TeamSimulationDTO> getById(@PathVariable Integer id) {
		TeamSimulation teamSimulation = service.GetById(id);
		return ResponseEntity.ok(mapper.toDTO(teamSimulation));
	}

	@PutMapping("/{id}")
	public ResponseEntity<TeamSimulationDTO> update(@PathVariable Integer id, @RequestBody TeamSimulationDTO teamSimulationDTO) {
		TeamSimulation updated = service.Update(id, teamSimulationDTO);
		return ResponseEntity.ok(mapper.toDTO(updated));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<TeamSimulationDTO> partialUpdate(@PathVariable Integer id, @RequestBody TeamSimulationDTO teamSimulationDTO) {
		TeamSimulation updated = service.PartialUpdate(id, teamSimulationDTO);
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

	@PatchMapping("/{id}/logical-delete")
	public ResponseEntity<String> logicalDelete(@PathVariable Integer id) {
		boolean result = service.LogicalDelete(id);
		if (result) {
			return ResponseEntity.ok("Team Simulation eliminado lógicamente");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo eliminar el Team Simulation lógicamente");
	}
}
