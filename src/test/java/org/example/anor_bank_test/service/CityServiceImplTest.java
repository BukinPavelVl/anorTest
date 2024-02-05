package org.example.anor_bank_test.service;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.example.anor_bank_test.dto.CityDtoRequest;
import org.example.anor_bank_test.dto.CityDtoResponse;
import org.example.anor_bank_test.mapper.CityMapper;
import org.example.anor_bank_test.model.City;
import org.example.anor_bank_test.repository.CityRepository;
import org.springframework.data.domain.Page;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CityServiceImplTest {
	@InjectMocks
	private CityServiceImpl cityService;
	@Mock
	private CityRepository cityRepository;
	@Mock
	private CityMapper cityMapper;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void getAllCities() {
		Pageable pageable = Pageable.unpaged();
		City city1 = new City();
		City city2 = new City();
		Page<City> cityPage = new PageImpl<>(List.of(city1, city2));
		when(cityRepository.findAll(pageable)).thenReturn(cityPage);
		when(cityMapper.cityToCityDtoResponse(city1)).thenReturn(new CityDtoResponse.Builder().cityName("Berlin").logo(null).build());
		when(cityMapper.cityToCityDtoResponse(city2)).thenReturn(new CityDtoResponse.Builder().cityName("Paris").logo(null).build());
		Page<CityDtoResponse> result = cityService.getAllCities(pageable);
		assertEquals(2, result.getSize());
	}
	
	@Test
	void getUniqueNames() {
		List<String> expectedUniqueCityNames = Arrays.asList("City1", "City2");
		when(cityRepository.findDistinctCityNames()).thenReturn(expectedUniqueCityNames);
		List<String> result = cityService.getUniqueCityNames();
		assertEquals(expectedUniqueCityNames, result);
	}
	
	@Test
	void testEditCity() throws IOException {
		Long cityId = 1L;
		CityDtoRequest cityDto = new CityDtoRequest.Builder().cityName("Berlin").countryName("Germany").logoUrl("https://upload.wikimedia.org/wikipedia/commons/b/b2/Logo_Paris_Cencosud.png").build();
		City existingCity = new City();
		byte[] logoData = null;
		try {
			URL imageUrl = new URL("https://upload.wikimedia.org/wikipedia/commons/b/b2/Logo_Paris_Cencosud.png");
			logoData = imageUrl.openStream().readAllBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		when(cityRepository.findById(cityId)).thenReturn(Optional.of(existingCity));
		when(cityMapper.cityToCityDtoResponse(any(City.class))).thenReturn(new CityDtoResponse.Builder().cityName("Berlin").logo(logoData).build());
		ResponseEntity<?> result = cityService.editCity(cityId, cityDto);
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}
	
	@Test
	void testCreateCity_Success() {
		
		CityDtoRequest cityDto = new CityDtoRequest.Builder().cityName("Berlin").countryName("Germany").logoUrl("https://upload.wikimedia.org/wikipedia/commons/b/b2/Logo_Paris_Cencosud.png").build();
		byte[] logoData = null;
		try {
			URL imageUrl = new URL("https://upload.wikimedia.org/wikipedia/commons/b/b2/Logo_Paris_Cencosud.png");
			logoData = imageUrl.openStream().readAllBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		City city = new City.Builder().cityName("Berlin").countryName("Germany").logo(logoData).build();
		when(cityMapper.cityDtoRequestToCity(cityDto)).thenReturn(city);
		when(cityRepository.save(city)).thenReturn(city);
		
		ResponseEntity<?> result = cityService.createCity(cityDto);
		assertEquals(HttpStatus.CREATED, result.getStatusCode());
	}
	
	@Test
	void testCreateCity_Error() {
		CityDtoRequest cityDto = new CityDtoRequest.Builder().cityName("Berlin").countryName("Germany").logoUrl("https://upload.wikimedia.org/wikipedia/commons/b/b2/Logo_Paris_Cencosud.png").build();
		when(cityMapper.cityDtoRequestToCity(cityDto)).thenThrow(RuntimeException.class);
		ResponseEntity<?> result = cityService.createCity(cityDto);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
	}
}
	
