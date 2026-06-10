package com.worldcup.worldcup.modules.simulations.group.controller;

import com.worldcup.worldcup.modules.simulations.group.dto.GroupDTO;
import com.worldcup.worldcup.modules.simulations.group.entity.Group;
import com.worldcup.worldcup.modules.simulations.group.services.interfaces.IGroup;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/groups")
public class GroupController {
	private final IGroup service;

	@PostMapping
	public ResponseEntity<String> create(@RequestBody GroupDTO groupDTO) {
		String result = service.Create(groupDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	@GetMapping
	public ResponseEntity<List<Group>> getAll() {
		return ResponseEntity.ok(service.GetAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Group> getById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.GetById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Group> update(@PathVariable Integer id, @RequestBody GroupDTO groupDTO) {
		return ResponseEntity.ok(service.Update(id, groupDTO));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Group> partialUpdate(@PathVariable Integer id, @RequestBody GroupDTO groupDTO) {
		return ResponseEntity.ok(service.PartialUpdate(id, groupDTO));
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
