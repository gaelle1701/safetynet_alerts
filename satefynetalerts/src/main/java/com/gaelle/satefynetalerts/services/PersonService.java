package com.gaelle.satefynetalerts.services;

import com.gaelle.satefynetalerts.dto.PersonDto;
import com.gaelle.satefynetalerts.entities.Person;
import com.gaelle.satefynetalerts.entities.PersonId;
import com.gaelle.satefynetalerts.entities.Role;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface PersonService {
    List<Person> getPersonsByBirthdate(String birthdate) throws ParseException;
    List<Person> getPersons();
    Person getPerson(PersonId personId);

    Person createPerson(PersonDto person, Role role);
    Person updatePerson(Person personToUpdate);
    void deletePerson(Long personId);

    List<Person> getPersonListByAddress(Long id);

    List<String> getEmailList(String city);


    List<String> getPhoneList(String phone);
}
