package com.example.demo.entity;
import com.example.demo.entity.ItemDetails;
import jakarta.validation.constraints.NotBlank;



import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items")
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Title must not be blank")
    @Column(name = "title")
     private String title;


     private int userId;


     @OneToOne(cascade = CascadeType.ALL)
     @JoinColumn(name ="item_details_id" )
     private ItemDetails itemDetails;









}
