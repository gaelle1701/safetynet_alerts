package com.gaelle.satefynetalerts.repositories;

import com.gaelle.satefynetalerts.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long>{
}
