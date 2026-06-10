package com.worldcup.worldCup.modules.mundial.country.controller;

import com.worldcup.worldCup.modules.mundial.country.dto.CountryDTO;
import com.worldcup.worldCup.modules.mundial.country.entity.Country;
import com.worldcup.worldCup.modules.mundial.country.services.interfaces.ICountry;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/countries")
public class CountryController {
	private final ICountry service;

	@PostMapping
	public ResponseEntity<String> create(@RequestBody CountryDTO countryDTO) {
		String result = service.CreateCountry(countryDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	@GetMapping
	public ResponseEntity<List<Country>> getAll() {
		return ResponseEntity.ok(service.GetAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Country> getById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.GetById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Country> update(@PathVariable Integer id, @RequestBody CountryDTO countryDTO) {
		return ResponseEntity.ok(service.Update(id, countryDTO));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Country> partialUpdate(@PathVariable Integer id, @RequestBody CountryDTO countryDTO) {
		return ResponseEntity.ok(service.PartialUpdate(id, countryDTO));
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
			return ResponseEntity.ok("País eliminado lógicamente");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo eliminar lógicamente");
	}
}
