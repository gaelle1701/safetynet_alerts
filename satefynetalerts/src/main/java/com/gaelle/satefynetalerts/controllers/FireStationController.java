package com.gaelle.satefynetalerts.controllers;

import com.gaelle.satefynetalerts.entities.Address;
import com.gaelle.satefynetalerts.entities.FireStation;
import com.gaelle.satefynetalerts.repositories.FireStationRepository;
import com.gaelle.satefynetalerts.services.AddressService;
import com.gaelle.satefynetalerts.services.FireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/firestation")
public class FireStationController {

    @Autowired
    FireStationRepository fireStationRepository;
    @Autowired
    FireStationService fireStationService;
    @Autowired
    AddressService addressService;

    @GetMapping("")
    public ResponseEntity<List<FireStation>> getFireStations(){
        List<FireStation> fireStationList = fireStationService.getFireStations();
        return ResponseEntity.ok(fireStationList);
    }

    @GetMapping("/{id}")
    public FireStation getFireStation(@PathVariable("id") Long id) {
        return fireStationService.getFireStation(id);
    }

    @PostMapping("/create")
    public ResponseEntity<FireStation> createFireStation(@RequestBody FireStation fireStation) {
        FireStation newFireStation = fireStationService.createFireStation(fireStation);
        System.out.println("newFS in post --> " + newFireStation);
        return new ResponseEntity<>(newFireStation, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FireStation> updateFireStation(@PathVariable("id") Long id, @RequestBody FireStation fireStationToUpdate){
        FireStation updatedFireStation = fireStationService.updateFireStation(fireStationToUpdate);
        return new ResponseEntity<>(updatedFireStation, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFireStation(@PathVariable("id") Long id) {
        fireStationService.deleteFireStation(id);
    }
}
