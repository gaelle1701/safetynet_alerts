package com.gaelle.satefynetalerts.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 2)
    private String address;

    @NotEmpty
    @Size(min = 2)
    private String city;

    @NotEmpty
    @Size(max = 5)
    private String zip;


    @Override
    public String toString() {
        return address + " " + city + " " + zip;
    }

}
