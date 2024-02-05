package org.example.anor_bank_test.service;

import org.example.anor_bank_test.dto.CityDtoRequest;
import org.example.anor_bank_test.dto.CityDtoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CityService {
	Page<CityDtoResponse> getAllCities(Pageable pageable);
	List<String> getUniqueCityNames();
	ResponseEntity<?> editCity(Long id, CityDtoRequest cityDto);
	ResponseEntity<?> createCity(CityDtoRequest cityDtoRequest);
}

