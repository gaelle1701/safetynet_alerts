package com.gaelle.satefynetalerts.services;

import com.gaelle.satefynetalerts.entities.Address;

import java.util.List;

public interface AddressService {

   Address getAddress(Long id);
   List<Address> getAddresses();
   Address createAddress(Address address);
   Address updateAddress(Address address);
   void deleteAddress(Long id);

   //    List<Address> getAddressBy(String genre)
}
