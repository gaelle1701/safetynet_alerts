package com.gaelle.satefynetalerts.entities;

import com.jsoniter.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Person {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "le nom ne peut être vide")
    @Size(min = 2, max = 32, message = "Le nom doit être compris entre 2 et 32 caractères")
    private String lastName;

    @NotEmpty(message = "le prénom ne peut être vide")
    @Size(min = 2, max = 32, message = "Le prénnom doit être compris entre 2 et 32 caractères")
    private String firstName;

    @NotEmpty
    private String email;

    @NotEmpty
    private String phone;

    @NotEmpty
    @JsonIgnore
    private String password;

    @NotEmpty
    private String birthdate;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> medicationList = new ArrayList<>();

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> allergyList = new ArrayList<>();

    @ManyToOne
    private Address address;

    @ManyToOne
    private Role role;


}
