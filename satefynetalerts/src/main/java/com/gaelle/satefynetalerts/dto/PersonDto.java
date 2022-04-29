package com.gaelle.satefynetalerts.dto;

import com.gaelle.satefynetalerts.entities.Address;
import com.gaelle.satefynetalerts.entities.PersonId;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PersonDto {
    private String lastName;
    private String phone;
    private String firstName;
    private Date birthdate;
    private String email;
    private String password;
    private Address address;
    private List<String> medicationList;
    private List<String> allergyList;

    public PersonId toPersonId(){
        return new PersonId(lastName, phone);
    }
}
