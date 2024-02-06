package br.com.araripe.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.araripe.exceptions.ResourceNotFoundException;
import br.com.araripe.model.Person;
import br.com.araripe.repositories.PersonRepository;

@Service
public class PersonServices {	
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	public List<Person> findAll() {
		
		logger.info("Finding all people!");
		
		List<Person> persons = new ArrayList<> ();		
		
		return repository.findAll();
	}	
	
	public Person findById(Long id) {
		
		logger.info("Finding onde person!");
				
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
	}
	
	public Person create(Person person) {
		
		logger.info("Creating one person");
		
		return repository.save(person);		
	}
	
   public Person update(Person person) {
		
		logger.info("Updating one person");
		
		Person entity = repository.findById(person.getId())
		.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLasttName(person.getLasttName());
		entity.setAdress(person.getAdress());
		entity.setGender(person.getGender());		
		
		return repository.save(person);		
	}
   
   public void delete (Long id) {
		
		logger.info("deleting one person");		
		
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		repository.delete(entity);
			
	}	
}
