package com.gaelle.satefynetalerts.services;

import com.gaelle.satefynetalerts.dto.PersonDto;
import com.gaelle.satefynetalerts.entities.Person;
import com.gaelle.satefynetalerts.entities.PersonId;
import com.gaelle.satefynetalerts.entities.Role;

import java.util.List;

public interface PersonService {
    List<Person> getPersonsByBirthdate(String birthdate);
    List<Person> getPersons();
    Person getPerson(PersonId personId);
    Person createPerson(PersonDto person, Role role);
    Person updatePerson(Person personToUpdate);
    void deletePerson(Long personId);
}
