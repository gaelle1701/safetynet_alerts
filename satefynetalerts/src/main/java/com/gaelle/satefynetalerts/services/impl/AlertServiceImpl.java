package com.gaelle.satefynetalerts.services.impl;

import com.gaelle.satefynetalerts.entities.*;
import com.gaelle.satefynetalerts.repositories.AlertRepository;
import com.gaelle.satefynetalerts.services.AlertService;
import com.gaelle.satefynetalerts.services.FireStationService;
import com.gaelle.satefynetalerts.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class AlertServiceImpl implements AlertService {
    @Autowired
    private AlertRepository alertRepository;
    @Autowired
    private FireStationService fireStationService;
    @Autowired
    private PersonService personService;


    @Override
    public List<Alert> getAlerts() {
        return alertRepository.findAll();
    }

    @Override
    public Alert getAlert(UUID num) {
        List<Alert> alertList = alertRepository.findAll();
        Alert currentAlert = new Alert();
        for (Alert alert: alertList) {
            int result = num.compareTo(alert.getNum());
            if (result == 0){
                currentAlert = alert;
            }
        }
        return currentAlert;
    }

    @Override
    public Alert createAlert(Alert alert) {
        LocalDate dateNow = LocalDate.now();
        StatusEnum status = StatusEnum.PENDING;
        FireStation fireStation = fireStationService.getFireStationByAddress(alert.getAddress().getId());
        List<Person> personList = personService.getPersonListByAddress(alert.getAddress().getId());

        Alert newAlert = new Alert();
        newAlert.setCreated_at(dateNow);
        newAlert.setDescription(alert.getDescription());
        newAlert.setStatus(status);
        newAlert.setType(alert.getType());
        newAlert.setAddress(alert.getAddress());
        newAlert.setFireStation(fireStation);
        newAlert.setAddress(alert.getAddress());
        newAlert.setPersonList(personList);

        this.alertRepository.save(newAlert);
        return newAlert;
    }

    @Override
    public Alert updateAlert(Alert alertToUpdate) {
        return alertRepository.save(alertToUpdate);
    }


}

