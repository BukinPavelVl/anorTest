package org.example.anor_bank_test.dto;

public record CityDtoResponse(String cityName, byte[] logo) {
	
	public String getCityName() {
		return cityName;
	}
	
	public byte[] getLogo() {
		return logo;
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		private String cityName;
		private byte[] logo;
		
		public Builder() {
		}
		
		
		public Builder cityName(final String cityName) {
			this.cityName = cityName;
			return this;
		}
		
		public Builder logo(final byte[] logo) {
			this.logo = logo;
			return this;
		}
		
		public CityDtoResponse build() {
			return new CityDtoResponse(this.cityName, this.logo);
		}
	}
}
