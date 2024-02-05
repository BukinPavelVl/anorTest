package org.example.anor_bank_test.controller;

import java.util.List;

import org.example.anor_bank_test.dto.CityDtoRequest;
import org.example.anor_bank_test.dto.CityDtoResponse;
import org.example.anor_bank_test.service.CityServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cities")
public class CityController {
	private final CityServiceImpl cityService;
	
	public CityController(CityServiceImpl cityService) {
		this.cityService = cityService;
		
	}
	
	@GetMapping
	public ResponseEntity<Page<CityDtoResponse>> getAllCities(Pageable pageable) {
		Page<CityDtoResponse> dtoPage = cityService.getAllCities(pageable);
		return ResponseEntity.ok(dtoPage);
	}
	
	@GetMapping("/unique-names")
	public ResponseEntity<List<String>> getUniqueCityNames() {
		List<String> uniqueCityNames = cityService.getUniqueCityNames();
		return ResponseEntity.ok(uniqueCityNames);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('EDITOR')")
	public ResponseEntity<?> editCity(@PathVariable Long id, @RequestBody CityDtoRequest cityDto) {
		return cityService.editCity(id, cityDto);
	}
	
	@PostMapping(consumes = "application/json")
	@PreAuthorize("hasRole('EDITOR')")
	public ResponseEntity<?> createCity(@RequestBody CityDtoRequest cityDtoRequest) {
		return cityService.createCity(cityDtoRequest);
	}
}
