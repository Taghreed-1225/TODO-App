package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Item_details")

public class ItemDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "description")
    private String description;


    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "priority")
    private int priority;

    @Column(name = "status")
    private boolean status;



}
