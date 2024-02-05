package org.example.anor_bank_test.dto;


public record CityDtoRequest(String cityName, String countryName, String logoUrl) {
	
	public String getCityName() {
		return cityName;
	}
	
	public String getCountryName() {
		return countryName;
	}

	public String getLogoUrl() {
		return logoUrl;
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		private String cityName;
		private String countryName;
		private String logoUrl;
		
		private Builder() {
		}
		
		
		public Builder cityName(final String cityName) {
			this.cityName = cityName;
			return this;
		}
		public Builder countryName(final String countryName) {
			this.countryName = countryName;
			return this;
		}
		
		public Builder logoUrl(final String logoUrl) {
			this.logoUrl = logoUrl;
			return this;
		}
		
		
		public CityDtoRequest build() {
			return new CityDtoRequest(this.cityName,this.countryName, this.logoUrl);
		}
	}
}

