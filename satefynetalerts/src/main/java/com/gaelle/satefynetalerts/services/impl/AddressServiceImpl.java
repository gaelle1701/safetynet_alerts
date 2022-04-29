package com.gaelle.satefynetalerts.services.impl;

import com.gaelle.satefynetalerts.entities.Address;
import com.gaelle.satefynetalerts.repositories.AddressRepository;
import com.gaelle.satefynetalerts.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressRepository addressRepository;

    @Override
    public Address getAddress(Long id) {
        Optional<Address> address = addressRepository.findById(id);
        System.out.println("Address by Id in service --> " +address);
        return null;
    }

    @Override
    public List<Address> getAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public Address createAddress(Address address) {
        try{
            Address newAddress = addressRepository.save(address);
            return newAddress;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public Address updateAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public void deleteAddress(Long id) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        Address address = null;
        if (addressOptional.isPresent()) {
            address = addressOptional.get();
            addressRepository.delete(address);
        }
    }
}
