package com.testepratico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testepratico.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
