package com.testepratico.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.testepratico.model.Address;
import com.testepratico.model.Person;
import com.testepratico.repository.AddressRepository;
import com.testepratico.repository.PersonRepository;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	PersonRepository personRepository;
	@Autowired
	AddressRepository addressRepository;
	
	@GetMapping
	public List<Person> getAll() {
		return personRepository.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Person getOne(@PathVariable Long id) {
		return personRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not exists"));
	}

	@GetMapping("/address/{id}")
	@ResponseStatus(HttpStatus.OK)
	public List<Address> getAddressPersonById(@PathVariable Long id) {
		try {
			List<Address> addressForPerson = new ArrayList<Address>();
			List<Long> addressId = personRepository.findById(id).get().getAddress();
			for (Long aID : addressId) {
				addressForPerson.add(addressRepository.findById(aID).get());
			}
			return addressForPerson;
		}catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not exists");
		}
	}

	@PostMapping
	public Person post(@RequestBody Person person) {
		return personRepository.save(person);
	}
	
	@PutMapping("/{id}")
    public Person patch(@PathVariable Long id, @RequestBody Person person) {
		try {
		Person modifyPerson =
				personRepository.findById(id).get()
    			.putPerson(person.getName(), person.getDateBirth(), person.getAddress());
    	return personRepository.saveAndFlush(modifyPerson);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not exists");
		}
    }

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		try {
			personRepository.deleteById(id);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not exists");
		}

	}

}