package com.gaelle.satefynetalerts.services.impl;

import com.gaelle.satefynetalerts.entities.Address;
import com.gaelle.satefynetalerts.entities.FireStation;
import com.gaelle.satefynetalerts.entities.Person;
import com.gaelle.satefynetalerts.repositories.AddressRepository;
import com.gaelle.satefynetalerts.repositories.FireStationRepository;
import com.gaelle.satefynetalerts.repositories.PersonRepository;
import com.gaelle.satefynetalerts.services.AddressService;
import com.gaelle.satefynetalerts.services.FireStationService;
import com.gaelle.satefynetalerts.services.PersonService;
import com.gaelle.satefynetalerts.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FireStationServiceImpl implements FireStationService {
    @Autowired
    private FireStationRepository fireStationRepository;
    @Autowired
    private AddressRepository addressRepository;



    @Override
    public FireStation getFireStation(Long id) {
        Optional<FireStation> fireStation = fireStationRepository.findById(id);
        return fireStation.get();
    }

    @Override
    public List<FireStation> getFireStations() {
        return fireStationRepository.findAll();
    }


    @Override
    public FireStation getFireStationByAddress(Long id) {
        List<FireStation> fireStations = fireStationRepository.findAll();
        Address currentAddress = addressRepository.findById(id).get();
        FireStation fireStationToReturn = null;
        for (FireStation fireStation : fireStations) {
            List<Address> addresses = fireStation.getAddressList();
            for (Address address : addresses) {
                if (address == currentAddress) {
                    fireStationToReturn = fireStation;
                }
            }
        }
        return fireStationToReturn;
    }

    @Override
    public FireStation createFireStation(FireStation fireStation) {
       List<Address> addressList = new ArrayList<>();
       FireStation newFireStation= new FireStation();
       for (Address address : fireStation.getAddressList()){
           Address existingAddress = addressRepository.getById(address.getId());
           addressList.add(existingAddress);
       }
       newFireStation.setStation(fireStation.getStation());
       newFireStation.setAddressList(addressList);
       fireStationRepository.save(newFireStation);
       return newFireStation;
    }

    @Override
    public FireStation updateFireStation(FireStation fireStation){
        return fireStationRepository.save(fireStation);
    }

    @Override
    public void deleteFireStation(Long id) {
        Optional<FireStation> fireStationOptional = fireStationRepository.findById(id);
        FireStation fireStation = null;
        if (fireStationOptional.isPresent()) {
            fireStation = fireStationOptional.get();
            fireStationRepository.delete(fireStation);
        }
    }
}
