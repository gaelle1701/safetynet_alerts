package com.gaelle.satefynetalerts.services.impl;

import com.gaelle.satefynetalerts.dto.PersonDto;
import com.gaelle.satefynetalerts.entities.Address;
import com.gaelle.satefynetalerts.entities.Person;
import com.gaelle.satefynetalerts.entities.PersonId;
import com.gaelle.satefynetalerts.entities.Role;
import com.gaelle.satefynetalerts.repositories.PersonRepository;
import com.gaelle.satefynetalerts.services.AddressService;
import com.gaelle.satefynetalerts.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private AddressService addressService;
    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Person> getPersonsByBirthdate(String birthdate) {
        return null;
    }

    @Override
    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person getPerson(PersonId personId) {
        Optional<Person> person = personRepository.findById(personId);
        return person.get();
    }

    @Override
    public Person createPerson(PersonDto personDto, Role role) {
        Person newPerson = new Person();
        Address address = addressService.getAddressById(personDto.getAddress());
        newPerson.setPersonId(personDto.toPersonId());
        newPerson.setFirstName(personDto.getFirstName());
        newPerson.setEmail(personDto.getEmail());
        newPerson.setPassword(personDto.getPassword());
        newPerson.setBirthdate(personDto.getBirthdate());
        newPerson.setMedicationList(personDto.getMedicationList());
        newPerson.setAllergyList(personDto.getAllergyList());
        newPerson.setAddress(address);
        newPerson.setRole(role);
        this.personRepository.save(newPerson);
        return newPerson;
    }

    @Override
    public Person updatePerson(Person personToUpdate) {
        return null;
    }

    @Override
    public void deletePerson(Long personId) {

    }
}
