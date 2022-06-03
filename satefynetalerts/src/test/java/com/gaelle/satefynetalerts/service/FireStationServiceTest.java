package com.gaelle.satefynetalerts.service;

import com.gaelle.satefynetalerts.entities.FireStation;
import com.gaelle.satefynetalerts.entities.Person;
import com.gaelle.satefynetalerts.services.FireStationService;
import com.gaelle.satefynetalerts.services.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FireStationServiceTest {

    @Mock
    FireStationService fireStationService;

    @Test
    public void deleteFireStation(){
        FireStation fireStation = new FireStation();
        fireStation.setId(7L);

        fireStationService.deleteFireStation(7L);

        Mockito.verify(fireStationService, Mockito.times(1)).deleteFireStation(7L);

    }
}
