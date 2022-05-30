package com.gaelle.satefynetalerts.services.impl;

import com.gaelle.satefynetalerts.dto.PersonDto;
import com.gaelle.satefynetalerts.entities.Address;
import com.gaelle.satefynetalerts.entities.Person;
import com.gaelle.satefynetalerts.entities.PersonId;
import com.gaelle.satefynetalerts.entities.Role;
import com.gaelle.satefynetalerts.repositories.AddressRepository;
import com.gaelle.satefynetalerts.repositories.PersonRepository;
import com.gaelle.satefynetalerts.services.AddressService;
import com.gaelle.satefynetalerts.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private AddressService addressService;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private PersonRepository personRepository;


    @Override
    public List<Person> getPersonsByBirthdate(String birthdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        List<Person> persons = personRepository.findAll();
        List<Person> personList = new ArrayList<>();
        for (Person person: persons) {
            Date currentBirthdate = sdf.parse(birthdate);
            int result = currentBirthdate.compareTo(person.getBirthdate());
            if(result==0){
                personList.add(person);
            }
        }
        return personList;
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
    public List<Person> getPersonListByAddress(Long id) {
        List<Person> persons = personRepository.findAll();
        List<Person> personList = new ArrayList<>();
        Address currentAddress = addressRepository.findById(id).get();
        for (Person person  : persons) {
            Address address = person.getAddress();
            if (address == currentAddress) {
                personList.add(person);
            }
        }
        return personList;
    }

    @Override
    public List<String> getEmailList(String city) {
        List<String> emailList = new ArrayList<>();

        for (Person person : personRepository.findAll()) {
            if (person.getAddress().getCity().equals(city))
                emailList.add(person.getEmail());
        }
        return emailList;
    }


    @Override
    public Person createPerson(PersonDto personDto, Role role) {
        Person newPerson = new Person();
        Address address = addressService.getAddress(personDto.getAddress());
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
