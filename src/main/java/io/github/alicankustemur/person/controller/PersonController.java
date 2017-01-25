package io.github.alicankustemur.person.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.alicankustemur.person.domain.Person;
import io.github.alicankustemur.person.service.PersonService;

@Component
@ManagedBean
public class PersonController {
	
	@Autowired
	private PersonService service;
	
	private List<Person> persons;
	
	@PostConstruct
	public void init(){
		persons = service.getAllPersons();
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
	
	
}
