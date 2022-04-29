//package com.gaelle.satefynetalerts.entities;
//
//import lombok.Data;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Data
//@Entity
//public class Type {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @Enumerated(EnumType.STRING)
//    private TypeEnum type;
//
//    @OneToMany(mappedBy = "type")
//    private List<Alert> alertList = new ArrayList<>();
//}
