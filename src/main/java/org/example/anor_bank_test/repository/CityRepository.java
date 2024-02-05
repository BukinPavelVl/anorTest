package org.example.anor_bank_test.repository;


import java.util.List;

import org.example.anor_bank_test.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
	@Query("SELECT DISTINCT c.cityName FROM City c")
	List<String> findDistinctCityNames();
	List<City> findByCountryName(String countryName);
	List<City> findByCityNameContainingIgnoreCase(String cityName);
	
}
