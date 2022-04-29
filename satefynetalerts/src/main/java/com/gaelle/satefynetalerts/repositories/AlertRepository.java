package com.gaelle.satefynetalerts.repositories;

import com.gaelle.satefynetalerts.entities.Address;
import com.gaelle.satefynetalerts.entities.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AlertRepository extends JpaRepository<Alert, UUID>{
}
