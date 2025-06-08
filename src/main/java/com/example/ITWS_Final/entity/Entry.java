//package com.example.ITWS_Final.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class Entry {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long entryId;
//
//    @ManyToOne
//    @JoinColumn(name = "store_id")
//    private Store store;
//
//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private Product product;
//
//    private Integer quantity;
//}
