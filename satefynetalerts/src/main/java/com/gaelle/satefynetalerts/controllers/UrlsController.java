package com.gaelle.satefynetalerts.controllers;

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


//    http://localhost:8080/childAlert?address=<address>
    @GetMapping("childAlert")
    public ResponseEntity<Map<String, Object>> getChildrenByAddress(@RequestParam Long address){
        Map<String, Object> childrenList = urlService.getChildrenList(address);
        return ResponseEntity.ok(childrenList);
    }
//    Cette url doit retourner une liste d'enfants (tout individu âgé de 18 ans ou moins) habitant à cette adresse.
//    La liste doit comprendre le prénom et le nom de famille de chaque enfant, son âge et une liste des autres
//    membres du foyer. S'il n'y a pas d'enfant, cette url peut renvoyer une chaîne vide.

//    http://localhost:8080/phoneAlert?firestation=<firestation_number>
//    Cette url doit retourner une liste des numéros de téléphone des résidents desservis par la caserne de
//    pompiers. Nous l'utiliserons pour envoyer des messages texte d'urgence à des foyers spécifiques.

//    http://localhost:8080/communityEmail?city=<city>
    @GetMapping("communityEmail")
    public ResponseEntity<List<String>> getEmailList(@RequestParam String city){
        List<String> emailList = personService.getEmailList(city);
        return ResponseEntity.ok(emailList);
    }
}
