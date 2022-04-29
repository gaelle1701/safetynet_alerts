package com.gaelle.satefynetalerts.utils;

import com.gaelle.satefynetalerts.entities.*;
import com.gaelle.satefynetalerts.repositories.AddressRepository;
import com.gaelle.satefynetalerts.repositories.FireStationRepository;
import com.gaelle.satefynetalerts.repositories.PersonRepository;
import com.gaelle.satefynetalerts.repositories.RoleRepository;
import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


@Component
public class Generator {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    FireStationRepository fireStationRepository;

    public void generateData() throws IOException {
        String filePath = "src/main/resources/data.json";
        byte[] bytesFile = Files.readAllBytes(new File(filePath).toPath());

        JsonIterator iter = JsonIterator.parse(bytesFile);
        Any any = iter.readAny();

        Any roleAny = any.get("roles");
        Role roleUser = generateRoles(roleAny);

        Any addressAny = any.get("addresses");
        generateAddress(addressAny);

        Any firestationAny = any.get("firestations");
        generateFirestation(firestationAny);

        Any personAny = any.get("persons");
        personAny.forEach(a -> {
            Address address = addressRepository.getById(a.get("addressId").toLong());

            List<Any> medications = a.get("medications").asList();
            List<String> medocList = new ArrayList<>();
            for (Any medoc : medications){
                medocList.add(medoc.toString());
            }

            List<Any> allergies = a.get("allergies").asList();
            List<String> allergyList = new ArrayList<>();
            for (Any allergy : allergies){
                allergyList.add(allergy.toString());
            }

            Person person = new Person(
                    null,
                    a.get("lastName").toString(),
                    a.get("email").toString(),
                    a.get("firstName").toString(),
                    a.get("phone").toString(),
                    a.get("password").toString(),
                    a.get("birthdate").toString(),
                    medocList,
                    allergyList,
                    address,
                    roleUser
            );
            this.personRepository.save(person);
        });
    }

    public Role generateRoles(Any roles) {
        Role roleUser = null;
        for(Any a: roles){
            String label = a.get("role").toString();
            RoleEnum rolesPerson = label.equals("USER") ? RoleEnum.USER : RoleEnum.ADMIN;
            Role role = new Role(
                null,
                rolesPerson
            );
            if (label.equals("USER")){
                roleUser = role;
            }
        this.roleRepository.save(role);
        };
        return roleUser;
    };

    public void generateAddress(Any addresses) {
        for (Any a: addresses){
            String addressLocal = a.get("address").toString();
            String city = a.get("city").toString();
            String zip = a.get("zip").toString();

            Address address = new Address(
                    null,
                    addressLocal,
                    city,
                    zip
            );
            this.addressRepository.save(address);
        };
    };

    public void generateFirestation(Any firestations) {
        for (Any a: firestations) {

            String station = a.get("station").toString();

            List<Any> addressList = a.get("addressList").asList();
            List<Address> addresses = new ArrayList<>();
            for (Any address : addressList){
                Address newAddress = new Address();
                newAddress.setId(address.get("id").toLong());
                newAddress.setAddress(address.get("address").toString());
                newAddress.setCity(address.get("city").toString());
                newAddress.setZip(address.get("zip").toString());

                addresses.add(newAddress);
            }

            FireStation fireStation = new FireStation(
                    null,
                    station,
                    addresses,
                    null
            );
            this.fireStationRepository.save(fireStation);
        }
    }



}
