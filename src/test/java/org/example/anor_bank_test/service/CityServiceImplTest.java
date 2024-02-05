package org.example.anor_bank_test.service;

import java.util.Arrays;
import java.util.List;

import org.example.anor_bank_test.mapper.CityMapper;
import org.example.anor_bank_test.repository.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
	}
	
	@Test
	void getUniqueNames() {
		List<String> expectedUniqueCityNames = Arrays.asList("City1", "City2");
		when(cityRepository.findDistinctCityNames()).thenReturn(expectedUniqueCityNames);
		List<String> result = cityService.getUniqueCityNames();
		assertEquals(expectedUniqueCityNames, result);
	}
}
	
