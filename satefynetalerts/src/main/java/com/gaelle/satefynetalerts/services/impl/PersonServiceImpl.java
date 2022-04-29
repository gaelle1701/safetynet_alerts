package com.gaelle.satefynetalerts.services.impl;

import com.gaelle.satefynetalerts.repositories.PersonRepository;
import com.gaelle.satefynetalerts.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonRepository personRepository;
}
