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
//public class Status {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @Enumerated(EnumType.STRING)
//    private StatusEnum status;
//
//    @OneToMany(mappedBy = "status")
//    private List<Alert> alertList = new ArrayList<>();
//}
