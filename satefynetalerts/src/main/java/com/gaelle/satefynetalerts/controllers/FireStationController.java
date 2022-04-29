package com.gaelle.satefynetalerts.controllers;

import com.gaelle.satefynetalerts.entities.FireStation;
import com.gaelle.satefynetalerts.repositories.FireStationRepository;
import com.gaelle.satefynetalerts.services.AddressService;
import com.gaelle.satefynetalerts.services.FireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/address")
    public FireStation getFireStationByAddress(@RequestParam(required = false) Long id) {
        FireStation fireStation = fireStationService.getFireStationByAddress(id);
        return fireStation;
    }

    @PostMapping("/create")
    public ResponseEntity<FireStation> createFireStation(@RequestBody FireStation fireStation) {
        FireStation newFireStation = fireStationService.createFireStation(fireStation);
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


//    http://localhost:8080/firestation?stationNumber=<station_number>
//    Cette url doit retourner une liste des personnes couvertes par la caserne de pompiers correspondante.
//    Donc, si le numéro de station = 1, elle doit renvoyer les habitants couverts par la station numéro 1.La liste
//    doit inclure les informations spécifiques suivantes : prénom, nom, adresse, numéro de téléphone. De plus,
//    elle doit fournir un décompte du nombre d'adultes et du nombre d'enfants (tout individu âgé de 18 ans ou
//            moins) dans la zone desservie.
}
