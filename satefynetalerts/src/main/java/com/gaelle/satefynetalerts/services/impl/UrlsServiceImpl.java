package com.gaelle.satefynetalerts.services.impl;

import com.gaelle.satefynetalerts.entities.Address;
import com.gaelle.satefynetalerts.entities.FireStation;
import com.gaelle.satefynetalerts.entities.Person;
import com.gaelle.satefynetalerts.repositories.AddressRepository;
import com.gaelle.satefynetalerts.repositories.FireStationRepository;
import com.gaelle.satefynetalerts.services.PersonService;
import com.gaelle.satefynetalerts.services.UrlsService;
import com.gaelle.satefynetalerts.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UrlsServiceImpl implements UrlsService {
    @Autowired
    DataUtils dataUtils;
    @Autowired
    private FireStationRepository fireStationRepository;
    @Autowired
    private PersonService personService;
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Map<String, Object> getChildrenList(Long id) {
        Map<String, Object> childrenList = new HashMap<>();
        Address address = addressRepository.getById(id);
        List<Person> personByAddress = new ArrayList<>();
        for(Person person : personService.getPersons()){
            if(person.getAddress().equals(address)){
                System.out.println("address to string " + person.getAddress());
                personByAddress.add(person);
                System.out.println("-- person --> " + address);
            } else {
                System.out.println("not ok");
            }
        }

        List<Person> children = new ArrayList<>();
        for (Person person : personByAddress){
            if (dataUtils.getAge(person) <= 18){
                children.add(person);
            }
        }
        for (Person person : children){
            if (dataUtils.getAge(person) <= 18){
                StringBuilder child = new StringBuilder();
                child.append(person.getFirstName() + " " + person.getPersonId().getLastName() + " " +
                        dataUtils.getAge(person) + " ans.");
                childrenList.put("Enfant: ", child);
            } else {
                childrenList.put("Enfant: ", "Pas d'enfant");
            }
        }
        return childrenList;
    }

    @Override
    public Map<String, Object> getFireStationByStationNumber(String station){
        FireStation fireStation = fireStationRepository.getFireStationByStation(station);
        List<Address> addressesInFireStation = fireStation.getAddressList();
        Map<String, Object> countPerson = new HashMap<>();
        List<String> infoPerson = new ArrayList<>();
        List<Person> personList = new ArrayList<>();
        Integer countAdults = 0;
        Integer countChildrens = 0;

        for (Address address : addressesInFireStation){
            List<Person> personsInAddress = personService.getPersonListByAddress(address.getId());
            personList.addAll(personsInAddress);
        }
        for (Person person : personList){
            Integer age = dataUtils.getAge(person);
            if (age > 18){
                countAdults++;
            } else {
                countChildrens++;
            }
            StringBuilder infos = new StringBuilder();
            infos.append(person.getFirstName());
            infos.append(person.getPersonId());
            infos.append(person.getAddress());
            infoPerson.add(infos.toString());
        }
        countPerson.put("Personnes", infoPerson);
        countPerson.put("Mineurs", countChildrens);
        countPerson.put("Majeurs", countAdults);

        return countPerson;
    }
}
