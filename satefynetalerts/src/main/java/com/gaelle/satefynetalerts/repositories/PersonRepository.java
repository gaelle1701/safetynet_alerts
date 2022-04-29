package com.gaelle.satefynetalerts.repositories;

import com.gaelle.satefynetalerts.entities.Person;
import com.gaelle.satefynetalerts.entities.PersonId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, PersonId>{
}
