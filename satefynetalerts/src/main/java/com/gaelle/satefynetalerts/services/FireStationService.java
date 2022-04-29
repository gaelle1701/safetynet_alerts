package com.gaelle.satefynetalerts.services;


import com.gaelle.satefynetalerts.entities.FireStation;

import java.util.List;

public interface FireStationService {
    FireStation getFireStation(Long id);

    List<FireStation> getFireStations();

    FireStation getFireStationByAddress(Long id);

    FireStation createFireStation(FireStation fireStation);

    FireStation updateFireStation(FireStation fireStation);

    void deleteFireStation(Long id);
}
