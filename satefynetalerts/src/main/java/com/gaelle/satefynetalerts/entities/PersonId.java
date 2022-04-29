package com.gaelle.satefynetalerts.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonId implements Serializable {

    @NotEmpty(message = "le nom ne peut être vide")
    @Size(min = 2, max = 32, message = "Le nom doit être compris entre 2 et 32 caractères")
    private String lastName;

    @NotEmpty
    private String phone;

    @Override
    public String toString() {
        return "PersonId{" +
                "lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

}
