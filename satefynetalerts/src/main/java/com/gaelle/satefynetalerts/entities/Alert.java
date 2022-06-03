package com.gaelle.satefynetalerts.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Alert {
    @Id
    private UUID num = UUID.randomUUID();

    @NotEmpty
    private String description;

    @NotNull
    private LocalDate created_at;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @Enumerated(EnumType.STRING)
    private TypeEnum type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Address address;

    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Person> personList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private FireStation fireStation;


}

