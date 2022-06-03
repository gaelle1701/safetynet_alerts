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

    @Override
    public List<String> getPhoneByFireStation(Long id){
        List<String> phoneList = new ArrayList<>();
        for (FireStation fireStation : fireStationRepository.findAll()){
            if (fireStation.getId().equals(id)){
                for (Address address : fireStation.getAddressList()){
                    for (Person person : personService.getPersons()){
                        if (address.equals(person.getAddress())){
                            phoneList.add(person.getPersonId().getPhone());
                        }
                    }
                }
            }

        }
        return phoneList;
    }

    @Override
    public Map<String, Object> getPersonByAddress(Long id) {
        Map<String, Object> result = new HashMap<>();
        List<Object> persons = new ArrayList<>();

        for (FireStation fireStation : fireStationRepository.findAll()) {
            for (Address address : fireStation.getAddressList()) {
                if (fireStation.getAddressList().equals(address)) {
                    result.put("Caserne désservant l'adresse: ", fireStation.getStation());
                }
            }
        }

        for (Person person : personService.getPersons()) {
            if (person.getAddress().getId().equals(id)) {
                StringBuilder personInfo = new StringBuilder();
                personInfo.append(person.getFirstName());
                personInfo.append(" ");
                personInfo.append(person.getPersonId().getLastName());
                personInfo.append(", phone: ");
                personInfo.append(person.getPersonId().getPhone());
                personInfo.append(", ");
                personInfo.append(dataUtils.getAge(person));
                personInfo.append("ans, allergies: ");
                personInfo.append(person.getAllergyList());
                personInfo.append(", médications: ");
                personInfo.append(person.getMedicationList());

                persons.add(personInfo);
            }
            result.put("Personnes à l'adresse: ", persons);
        }
        return result;
    }

    @Override
    public Map<String, Object> getPersonsByFirestation(List<Long> stations) {
        Map<String, Object> caserne = new HashMap<>();
        List<Object> caserneList = new ArrayList<>();
        Map<String, Object> caserneAndInfos = new HashMap<>();

        //Get la firestation
        List<FireStation> fireStationList = new ArrayList<>();
        for (Long station : stations) {
            System.out.println("STATION ---------> " + station);
            FireStation fireStation = fireStationRepository.getById(station);
            fireStationList.add(fireStation);

            //Boucle sur la liste d'addresses de cette firestation et on récupère la liste de personnes à cette adresse grace à la méthode précédente
            for (FireStation f : fireStationList){
                System.out.println("firestation --->" + f);
                Map<String, Object> addressesList = new HashMap<>();
                List<Object> addressAndPersons = new ArrayList<>();

                for (Address a : f.getAddressList()){
                    Map<String, Object> personsInAddress = new HashMap<>();
                    Map<String, Object> people = this.getPersonByAddress(a.getId());

                    personsInAddress.put("adresse", a);
                    personsInAddress.put("Liste de personnes", people);
                    addressAndPersons.add(personsInAddress);
                }
                addressesList.put("Liste adresses et personnes ", addressAndPersons);

                caserneAndInfos.put("Caserne : ", f.getStation());
                caserneAndInfos.put("Infos", addressesList);
            }
            caserneList.add(caserneAndInfos);
        }
        System.out.println("caserneList --->" + caserneList);
        caserne.put("Liste de casernes", caserneList);

        return caserne;
    }
}
