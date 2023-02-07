package com.testepratico;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testepratico.model.Address;
import com.testepratico.model.Person;

@SpringBootTest
@AutoConfigureMockMvc
class RestApiPersonsApplicationTests {
	
	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    //_testPostAddress
    public void case1() throws Exception {
    	Long id = 1L;
    	String street = "Rua A";
    	Long zipcode = 60000000L;
    	Integer number = 100;
    	String city = "City Example";
    	Boolean principal = true;
		
		Address address = new Address(id, street, zipcode, number, city, principal);
		
		mockMvc.perform(post("/address")
		        .contentType("application/json")
		        .content(objectMapper.writeValueAsString(address)))
		        .andExpect(status().is2xxSuccessful());
    }
    
    @Test
    //_testGetAllAddress
	public void case2() throws Exception {	
		mockMvc.perform(get("/address"))
			.andExpect(status().isOk());
	}
    
    @Test
    //_testGetByIdAddress
    public void case3() throws Exception {
    	mockMvc.perform(get("/address/1"))
		.andExpect(status().isOk());
    }
    
    @Test
    //_testPutAddress
    public void case4() throws Exception {
    	Long id = 1L;
    	String street = "Rua B";
    	Long zipcode = 60000001L;
    	Integer number = 101;
    	String city = "City Example 2";
    	Boolean principal = false;
		
		Address address = new Address(id, street, zipcode, number, city, principal);
		
		mockMvc.perform(put("/address/1")
		        .contentType("application/json")
		        .content(objectMapper.writeValueAsString(address)))
		        .andExpect(status().is2xxSuccessful());
    }

    @Test
    //_testPostPerson
    public void case5() throws Exception {
    	Long id = 1L;
		String name = "José";
		String dateBirth = "01011990";
		List<Long> address = new ArrayList<>();
		address.add(1L);
		address.add(2L);
		
		Person person = new Person(id, name, dateBirth, address);
		
		mockMvc.perform(post("/person")
		        .contentType("application/json")
		        .content(objectMapper.writeValueAsString(person)))
		        .andExpect(status().is2xxSuccessful());
    }
    
    @Test
    //_testGetAllPersons
	public void case6() throws Exception {	
		mockMvc.perform(get("/person"))
			.andExpect(status().isOk());
	}
    
    @Test
    //_testGetByIdPerson
    public void case7() throws Exception {
    	mockMvc.perform(get("/person/1"))
		.andExpect(status().isOk());
    }
    
    @Test
    //_testPutPerson
    public void case8() throws Exception {
    	Long id = 1L;
		String name = "José";
		String dateBirth = "01011990";
		List<Long> address = new ArrayList<>();
		address.add(1L);
		
		Person person = new Person(id, name, dateBirth, address);
		
		mockMvc.perform(put("/person/1")
		        .contentType("application/json")
		        .content(objectMapper.writeValueAsString(person)))
		        .andExpect(status().is2xxSuccessful());
    }
    
    @Test
    //_testDeleteAddress
    public void case9() throws Exception {
    	mockMvc.perform(delete("/address/1"))
		.andExpect(status().is2xxSuccessful());
    }

}
