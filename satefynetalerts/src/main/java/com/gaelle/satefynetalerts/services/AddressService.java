package com.gaelle.satefynetalerts.services;

import com.gaelle.satefynetalerts.entities.Address;

import java.util.List;

public interface AddressService {

   Address getAddress(Address address);
   List<Address> getAddresses();
   Address createAddress(Address address);
   Address updateAddress(Address addressToUpdate);
//   void deleteAddress(Long id);
}
