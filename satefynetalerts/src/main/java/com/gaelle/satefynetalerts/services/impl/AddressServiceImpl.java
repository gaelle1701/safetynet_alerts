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
    private AddressRepository addressRepository;

    @Override
    public List<Address> getAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public Address getAddress(Address address){
        return addressRepository.getById(address.getId());
    }

    @Override
    public Address createAddress(Address address) {
        List<Address> addressList = addressRepository.findAll();
        for (Address existingAddress: addressList) {
            if((existingAddress.getAddress() != address.getAddress())
                    && (existingAddress.getCity() != address.getCity())
                    && (existingAddress.getZip() != address.getZip()))
            {
                addressRepository.save(address);
            }else{
                System.out.println("Cette adresse existe déjà !");
            }
        }
        return address;
    }

    @Override
    public Address updateAddress(Address address) {
        return addressRepository.save(address);
    }
//
//    @Override
//    public void deleteAddress(Long id) {
//
//    }

}
