package org.example.anor_bank_test.mapper;

import java.io.IOException;

import org.example.anor_bank_test.dto.CityDtoRequest;
import org.example.anor_bank_test.dto.CityDtoResponse;
import org.example.anor_bank_test.model.City;
import org.example.anor_bank_test.utils.ImageDownloader;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CityMapper {
	@Mapping(source = "cityName", target = "cityName")
	@Mapping(source = "countryName", target = "countryName")
	@Mapping(source = "logoUrl", target = "logo", qualifiedByName = "urlToByte")
	@Mapping(target = "id", ignore = true)
	City cityDtoRequestToCity(CityDtoRequest dto);
	
	CityDtoResponse cityToCityDtoResponse(City city);
	
	@Named("urlToByte")
	static byte[] urlToByte(String urlString) {
		if (urlString == null || urlString.isEmpty()) {
			return null;
		}
		try {
			return ImageDownloader.downloadImage(urlString);
		} catch (IOException e) {
			throw new RuntimeException("Failed to download image from URL: " + urlString, e);
		}
	}
}


