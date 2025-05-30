package com.example.demo.entity;
import com.example.demo.entity.ItemDetails;
import jakarta.validation.constraints.NotBlank;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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







     //lombok


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ItemDetails getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(ItemDetails itemDetailsId) {
        this.itemDetails = itemDetailsId;
    }

    public Items(String title, int userId, ItemDetails itemDetailsId) {
        this.title = title;
        this.userId = userId;
        this.itemDetails = itemDetailsId;
    }
    public Items() {

    }

}
