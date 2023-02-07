package com.testepratico.controller;

import com.testepratico.model.Address;
import com.testepratico.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressRepository repository;

    @GetMapping
    public List<Address> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Address getOne(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not exists"));
    }

    @PostMapping
    public Address post(@RequestBody Address address) {
        return repository.save(address);
    }
    
    @PutMapping("/{id}")
    public Address patch(@PathVariable Long id, @RequestBody Address address) {
    	try {
    	Address modifyAddress = 
    			repository.findById(id).get()
    			.putAddress(address.getStreet(), address.getZipcode(), address.getNumber(), 
    						address.getCity(), address.getPrincipal());
    	return repository.saveAndFlush(modifyAddress);
	    } catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not exists");
		}
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not exists");
        }
    }

}