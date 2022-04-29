package com.gaelle.satefynetalerts.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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

    @Temporal(TemporalType.DATE)
    private Date created_at;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @Enumerated(EnumType.STRING)
    private TypeEnum type;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Person> personList = new ArrayList<>();

//    @ManyToOne
//    private Type type;


}
