package com.testepratico.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String dateBirth;
	@ElementCollection(targetClass=Long.class)
	private List<Long> address;

	public Person() {
	}

	public Person(Long id, String name, String dateBirth, List<Long> address) {
		this.id = id;
		this.name = name;
		this.dateBirth = dateBirth;
		this.address = (List<Long>) address;
    }
	
	public Person putPerson(String name, String dateBirth, List<Long> address) {
		this.name = name;
		this.dateBirth = dateBirth;
		this.address = address;
		
		return this;		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(String dateBirth) {
		this.dateBirth = dateBirth;
	}

	public List<Long> getAddress() {
		return address;
	}

	public void setAddress(List<Long> address) {
		this.address = address;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Person person = (Person) o;
		return Objects.equals(id, person.id) && Objects.equals(name, person.name) && Objects.equals(dateBirth, person.dateBirth) && Objects.equals(address, person.address);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, dateBirth, address);
	}
}
