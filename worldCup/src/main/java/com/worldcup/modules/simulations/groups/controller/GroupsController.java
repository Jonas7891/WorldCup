package com.worldcup.modules.simulations.groups.controller;

import com.worldcup.modules.simulations.groups.dto.GroupsDTO;
import com.worldcup.modules.simulations.groups.entity.Groups;
import com.worldcup.modules.simulations.groups.services.interfaces.IGroups;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/groups")
public class GroupsController {
	private final IGroups service;

	@PostMapping
	public ResponseEntity<String> create(@RequestBody GroupsDTO groupsDTO) {
		String result = service.Create(groupsDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	@GetMapping
	public ResponseEntity<List<Groups>> getAll() {
		return ResponseEntity.ok(service.GetAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Groups> getById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.GetById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Groups> update(@PathVariable Integer id, @RequestBody GroupsDTO groupsDTO) {
		return ResponseEntity.ok(service.Update(id, groupsDTO));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Groups> partialUpdate(@PathVariable Integer id, @RequestBody GroupsDTO groupsDTO) {
		return ResponseEntity.ok(service.PartialUpdate(id, groupsDTO));
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
