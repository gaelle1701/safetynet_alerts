package com.gaelle.satefynetalerts.repositories;

import com.gaelle.satefynetalerts.entities.Person;
import com.gaelle.satefynetalerts.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer>{
}
