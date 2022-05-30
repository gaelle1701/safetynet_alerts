package com.gaelle.satefynetalerts.services;

import com.gaelle.satefynetalerts.entities.Alert;

import java.util.List;
import java.util.UUID;

public interface AlertService {
    List<Alert> getAlerts();

    Alert getAlert(UUID num);

    Alert createAlert(Alert alert);

    Alert updateAlert(Alert alertToUpdate);

}
