package com.gaelle.satefynetalerts.controllers;

import com.gaelle.satefynetalerts.entities.FireStation;
import com.gaelle.satefynetalerts.entities.Person;
import com.gaelle.satefynetalerts.services.FireStationService;
import com.gaelle.satefynetalerts.services.PersonService;
import com.gaelle.satefynetalerts.services.UrlsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/")
public class UrlsController {

    @Autowired
    UrlsService urlService;
    @Autowired
    PersonService personService;
    @Autowired
    FireStationService fireStationService;


//    http://localhost:8080/childAlert?address=<address>
//    Cette url doit retourner une liste d'enfants (tout individu âgé de 18 ans ou moins) habitant à cette adresse.
//    La liste doit comprendre le prénom et le nom de famille de chaque enfant, son âge et une liste des autres
//    membres du foyer. S'il n'y a pas d'enfant, cette url peut renvoyer une chaîne vide.
    @GetMapping("childAlert")
    public ResponseEntity<Map<String, Object>> getChildrenByAddress(@RequestParam Long address){
        Map<String, Object> childrenList = urlService.getChildrenList(address);
        return ResponseEntity.ok(childrenList);
    }

//    http://localhost:8080/phoneAlert?firestation=<firestation_number>
//    Cette url doit retourner une liste des numéros de téléphone des résidents desservis par la caserne de
//    pompiers. Nous l'utiliserons pour envoyer des messages texte d'urgence à des foyers spécifiques.
    @GetMapping("phoneAlert")
    public ResponseEntity<List<String>> getPhoneList(@RequestParam Long firestation){
        List<String> phoneList = urlService.getPhoneByFireStation(firestation);
        return  ResponseEntity.ok(phoneList);
    }

//    http://localhost:8080/communityEmail?city=<city>
    @GetMapping("communityEmail")
    public ResponseEntity<List<String>> getEmailList(@RequestParam String city){
        List<String> emailList = personService.getEmailList(city);
        return ResponseEntity.ok(emailList);
    }

//    http://localhost:8080/fire?address=<address>
//    Cette url doit retourner la liste des habitants vivant à l’adresse donnée ainsi que le numéro de la caserne
//    de pompiers la desservant. La liste doit inclure le nom, le numéro de téléphone, l'âge et les antécédents
//    médicaux (médicaments, posologie et allergies) de chaque personne.
    @GetMapping("fire")
    public ResponseEntity<Map<String, Object>> getPersonInAddress(@RequestParam Long address){
        Map<String, Object> personList = urlService.getPersonByAddress(address);
        return ResponseEntity.ok(personList);
    }

//    http://localhost:8080/flood/stations?stations=<a list of station_numbers>
//    Cette url doit retourner une liste de tous les foyers desservis par la caserne. Cette liste doit regrouper les
//    personnes par adresse. Elle doit aussi inclure le nom, le numéro de téléphone et l'âge des habitants, et
//    faire figurer leurs antécédents médicaux (médicaments, posologie et allergies) à côté de chaque nom.
    @GetMapping("/flood/stations")
    public ResponseEntity<Map<String, Object>> getPersonsByFirestation(@RequestParam List<Long> stations){
        Map<String, Object> personsInfos = urlService.getPersonsByFirestation(stations);
        return ResponseEntity.ok(personsInfos);
    }

//    http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
//    Cette url doit retourner le nom, l'adresse, l'âge, l'adresse mail et les antécédents médicaux (médicaments,
//    posologie, allergies) de chaque habitant. Si plusieurs personnes portent le même nom, elles doivent
//    toutes apparaître.
}
