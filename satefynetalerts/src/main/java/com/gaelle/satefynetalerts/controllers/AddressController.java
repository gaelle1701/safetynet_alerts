package com.gaelle.satefynetalerts.controllers;

import com.gaelle.satefynetalerts.entities.Address;
import com.gaelle.satefynetalerts.repositories.AddressRepository;
import com.gaelle.satefynetalerts.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/address")
public class AddressController {

    @Autowired
    AddressRepository addressRepository;
    @Autowired
    AddressService addressService;


    @GetMapping("")
    public ResponseEntity<List<Address>> getAdresses() {
        List<Address> addressList = addressService.getAddresses();
        return new ResponseEntity<>(addressList, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        Address newAddress = addressService.createAddress(address);
        return new ResponseEntity<>(newAddress, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable("id") Long id, @RequestBody Address addressToUpdate){
        Address updatedAddress = addressService.updateAddress(addressToUpdate);
        return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAddress(@PathVariable("id") Long id) {
        addressService.deleteAddress(id);
    }
}
