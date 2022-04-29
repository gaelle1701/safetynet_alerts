package com.gaelle.satefynetalerts.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jsoniter.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Person {

    @EmbeddedId
    private PersonId personId;

    @NotEmpty(message = "le prénom ne peut être vide")
    @Size(min = 2, max = 32, message = "Le prénnom doit être compris entre 2 et 32 caractères")
    private String firstName;

    @NotEmpty
    private String email;

    @NotEmpty
    @JsonIgnore
    private String password;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthdate;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> medicationList = new ArrayList<>();

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> allergyList = new ArrayList<>();

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne
    private Address address;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Role role;

}
