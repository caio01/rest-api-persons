package com.testepratico.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	String street;
	Long zipcode;
	Integer number;
	String city;
	Boolean principal;

	public Address(Long id, String street, Long zipcode, Integer number, String city, Boolean principal) {
		this.id = id;
		this.street = street;
		this.zipcode = zipcode;
		this.number = number;
		this.city = city;
		this.principal = principal;
	}

	public Address(){}
	
	public Address putAddress(String street, Long zipcode, Integer number, String city, Boolean principal) {
		this.street = street;
		this.zipcode = zipcode;
		this.number = number;
		this.city = city;
		this.principal = principal;
		
		return this;		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Long getZipcode() {
		return zipcode;
	}

	public void setZipcode(Long zipcode) {
		this.zipcode = zipcode;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Boolean getPrincipal() {
		return principal;
	}

	public void setPrincipal(Boolean principal) {
		this.principal = principal;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Address address = (Address) o;
		return Objects.equals(id, address.id) && Objects.equals(street, address.street) && Objects.equals(zipcode, address.zipcode) && Objects.equals(number, address.number) && Objects.equals(city, address.city) && Objects.equals(principal, address.principal);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, street, zipcode, number, city, principal);
	}
}
