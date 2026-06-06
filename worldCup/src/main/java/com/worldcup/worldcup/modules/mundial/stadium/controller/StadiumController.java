package com.worldcup.worldcup.modules.mundial.stadium.controller;

import com.worldcup.worldcup.modules.mundial.stadium.dto.StadiumDTO;
import com.worldcup.worldcup.modules.mundial.stadium.entity.Stadium;
import com.worldcup.worldcup.modules.mundial.stadium.services.interfaces.IStadium;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/stadiums")
public class StadiumController {
	private final IStadium service;

	@PostMapping
	public ResponseEntity<String> create(@RequestBody StadiumDTO stadiumDTO) {
		String result = service.Create(stadiumDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	@GetMapping
	public ResponseEntity<List<Stadium>> getAll() {
		return ResponseEntity.ok(service.GetAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Stadium> getById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.GetById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Stadium> update(@PathVariable Integer id, @RequestBody StadiumDTO stadiumDTO) {
		return ResponseEntity.ok(service.Update(id, stadiumDTO));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Stadium> partialUpdate(@PathVariable Integer id, @RequestBody StadiumDTO stadiumDTO) {
		return ResponseEntity.ok(service.PartialUpdate(id, stadiumDTO));
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
			return ResponseEntity.ok("Estadio eliminado lógicamente");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo eliminar el estadio lógicamente");
	}
}
