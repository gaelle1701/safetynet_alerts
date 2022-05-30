package com.gaelle.satefynetalerts.utils;

import com.gaelle.satefynetalerts.entities.Person;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

@Component
public class DataUtils {

    public int getAge(Person person){
        LocalDate today = LocalDate.now();
        LocalDate birthdate = person.getBirthdate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period age = Period.between(birthdate, today);
        return age.getYears();
    }
}
