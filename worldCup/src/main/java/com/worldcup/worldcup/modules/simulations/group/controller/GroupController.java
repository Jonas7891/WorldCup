package com.worldcup.worldcup.modules.mundial.team.controller;

import com.worldcup.worldcup.modules.mundial.team.dto.TeamDTO;
import com.worldcup.worldcup.modules.mundial.team.entity.Team;
import com.worldcup.worldcup.modules.mundial.team.services.interfaces.ITeam;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/teams")
public class TeamController {
	private final ITeam service;

	@PostMapping
	public ResponseEntity<String> create(@RequestBody TeamDTO teamDTO) {
		String result = service.Create(teamDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	@GetMapping
	public ResponseEntity<List<Team>> getAll() {
		return ResponseEntity.ok(service.GetAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Team> getById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.GetById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Team> update(@PathVariable Integer id, @RequestBody TeamDTO teamDTO) {
		return ResponseEntity.ok(service.Update(id, teamDTO));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Team> partialUpdate(@PathVariable Integer id, @RequestBody TeamDTO teamDTO) {
		return ResponseEntity.ok(service.PartialUpdate(id, teamDTO));
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
			return ResponseEntity.ok("Equipo eliminado lógicamente");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo eliminar lógicamente");
	}
}
