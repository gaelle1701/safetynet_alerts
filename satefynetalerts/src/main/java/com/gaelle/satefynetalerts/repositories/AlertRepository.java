package com.gaelle.satefynetalerts.repositories;

import com.gaelle.satefynetalerts.entities.Address;
import com.gaelle.satefynetalerts.entities.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<Alert, Long>{
}
