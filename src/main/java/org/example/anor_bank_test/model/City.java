package org.example.anor_bank_test.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "tab_city")
public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "city_name")
	private String cityName;
	
	@Column(name = "country_name")
	private String countryName;
	//	@Lob
	@Column(name = "logo", columnDefinition = "bytea")
	private byte[] logo;
	
	public City(final Long id, final String cityName, final String countryName, final byte[] logo) {
		this.id = id;
		this.cityName = cityName;
		this.countryName = countryName;
		this.logo = logo;
	}
	
	public City() {
	}
	
	public Long getId() {
		return id;
	}
	
	public String getCityName() {
		return cityName;
	}
	
	public String getCountryName() {
		return countryName;
	}
	
	public byte[] getLogo() {
		return logo;
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		private Long id;
		private String cityName;
		private String countryName;
		private byte[] logo;
		
		public Builder() {
		}
		
		public Builder id(final Long id) {
			this.id = id;
			return this;
		}
		
		public Builder cityName(final String cityName) {
			this.cityName = cityName;
			return this;
		}
		
		public Builder countryName(final String countryName) {
			this.countryName = countryName;
			return this;
		}
		
		public Builder logo(final byte[] logo) {
			this.logo = logo;
			return this;
		}
		
		
		public City build() {
			return new City(this.id, this.cityName, this.countryName, this.logo);
		}
	}
}

