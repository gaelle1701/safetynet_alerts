package com.gaelle.satefynetalerts.repositories;

import com.gaelle.satefynetalerts.entities.Address;
import com.gaelle.satefynetalerts.entities.FireStation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FireStationRepository extends JpaRepository<FireStation, Long>{
}
