package com.gaelle.satefynetalerts.controllers;

import com.gaelle.satefynetalerts.entities.Alert;
import com.gaelle.satefynetalerts.repositories.AlertRepository;
import com.gaelle.satefynetalerts.services.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/alert")
public class AlertController {
    @Autowired
    private AlertService alertService;

    @GetMapping("")
    public ResponseEntity<List<Alert>> getAlerts(){
        List<Alert> alertList = alertService.getAlerts();
        return new ResponseEntity<>(alertList, HttpStatus.OK);
    }

    @GetMapping("/{num}")
    public Alert getAlert(@PathVariable("num") UUID num){
        return alertService.getAlert(num);
    }

    @PostMapping("/create")
    public ResponseEntity<Alert> createAlert(@RequestBody Alert alert) {
        Alert newAlert = alertService.createAlert(alert);
        return new ResponseEntity<>(newAlert, HttpStatus.CREATED);
    }
//    INSERT INTO `alert` (`num`, `created_at`, `description`, `status`, `type`, `fire_station_id`) VALUES (0x1234,
//    '2022-04-29', 'Blabla', 'PENDING', 'FIRE', '1');

    @PutMapping("update/{num}")
    public ResponseEntity<Alert> updateAddress(@PathVariable("num") UUID num, @RequestBody Alert alertToUpdate){
        Alert updatedAlert = alertService.updateAlert(alertToUpdate);
        return new ResponseEntity<>(updatedAlert, HttpStatus.OK);
    }
}
