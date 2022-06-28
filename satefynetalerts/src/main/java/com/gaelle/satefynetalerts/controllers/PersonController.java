package com.gaelle.satefynetalerts.controllers;

import com.gaelle.satefynetalerts.dto.PersonDto;
import com.gaelle.satefynetalerts.entities.Address;
import com.gaelle.satefynetalerts.entities.Person;
import com.gaelle.satefynetalerts.entities.PersonId;
import com.gaelle.satefynetalerts.entities.Role;
import com.gaelle.satefynetalerts.repositories.RoleRepository;
import com.gaelle.satefynetalerts.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("api/person")
public class PersonController {
    @Autowired
    private PersonService personService;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("")
    public ResponseEntity<List<Person>> getPersons(@RequestParam(required = false) String birthdate) throws ParseException {
        List<Person> persons;
        if (birthdate != null)  {
            persons = personService.getPersonsByBirthdate(birthdate);
        } else {
            persons = personService.getPersons();
        }
        return ResponseEntity.ok(persons);
    }

    @GetMapping("/{lastname}/{phone}")
    public Person getPerson(@PathVariable("lastname") String lastname, @PathVariable("phone") String phone) {
        PersonId personId = new PersonId(lastname, phone);
        return personService.getPerson(personId);
    }

    @GetMapping("/address")
    public ResponseEntity<List<Person>> getPersonListByAddress(@RequestParam(required = false) Long id) {
        List<Person> personList = personService.getPersonListByAddress(id);
        return ResponseEntity.ok(personList);
    }

    @PostMapping("create/person")
    public ResponseEntity<Person> createPerson(@RequestBody PersonDto personDto) {
        Role role = roleRepository.getById(1);
        Person newPerson = personService.createPerson(personDto, role);
        return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
    }

    @PostMapping("create/admin")
    public ResponseEntity<Person> createAdmin(@RequestBody PersonDto person) {
        Role role = roleRepository.getById(2);
        Person newPerson = personService.createPerson(person, role);
        return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
    }

    @PutMapping("update/{personId}")
    public ResponseEntity<Person> updatePerson(@PathVariable("personId") Long personId, @RequestBody Person personToUpdate){
        Person updatedPerson = personService.updatePerson(personToUpdate);
        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }

    @DeleteMapping("delete/{personId}")
    public void deletePersonById(@PathVariable("personId") Long personId) {
        personService.deletePerson(personId);
    }
}
