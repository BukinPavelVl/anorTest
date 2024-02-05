package org.example.anor_bank_test.configs;

import org.example.anor_bank_test.mapper.CityMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapStructConfig {
	@Bean
	public CityMapper cityMapper() {
		return Mappers.getMapper(CityMapper.class);
	}
}

