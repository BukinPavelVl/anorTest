package org.example.anor_bank_test.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.example.anor_bank_test.dto.CityDtoRequest;
import org.example.anor_bank_test.dto.CityDtoResponse;
import org.example.anor_bank_test.mapper.CityMapper;
import org.example.anor_bank_test.model.City;
import org.example.anor_bank_test.repository.CityRepository;
import org.example.anor_bank_test.utils.ImageDownloader;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class CityServiceImpl implements CityService {
	
	private final CityRepository cityRepository;
	private final CityMapper cityMapper;
	
	public CityServiceImpl(final CityRepository cityRepository, final CityMapper cityMapper) {
		this.cityRepository = cityRepository;
		this.cityMapper = cityMapper;
	}
	
	@Override
	public Page<CityDtoResponse> getAllCities(Pageable pageable) {
		Page<City> cityPage = cityRepository.findAll(pageable);
		return cityPage.map(cityMapper::cityToCityDtoResponse);
	}
	
	@Override
	public List<String> getUniqueCityNames() {
		return cityRepository.findDistinctCityNames();
	}
	
	@Override
	@Transactional
	public ResponseEntity<?> editCity(Long id, CityDtoRequest cityDto) {
		Optional<City> existingCityOptional = cityRepository.findById(id);
		if (existingCityOptional.isPresent()) {
			City existingCity = existingCityOptional.get();
			City updatedCity;
			try {
				updatedCity = City.builder()
						.id(existingCity.getId())
						.cityName(cityDto.getCityName())
						.countryName(existingCity.getCountryName())
						.logo(ImageDownloader.downloadImage(cityDto.getLogoUrl()))
						.build();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			updatedCity = cityRepository.save(updatedCity);
			CityDtoResponse responseDto = cityMapper.cityToCityDtoResponse(updatedCity);
			return ResponseEntity.ok(responseDto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Override
	@Transactional
	public ResponseEntity<?> createCity(CityDtoRequest cityDtoRequest) {
		try {
			City city = cityMapper.cityDtoRequestToCity(cityDtoRequest);
			city = cityRepository.save(city);
			return new ResponseEntity<>(city, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating city: " + e.getMessage());
		}
	}
}
