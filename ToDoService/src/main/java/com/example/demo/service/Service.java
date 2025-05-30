package com.example.demo.service;


import com.example.demo.entity.Items;
import org.springframework.data.domain.Page;

public interface Service {

    public Page<Items> getItems(int page , int size);

    public void addItem(Items items);

    public void deleteItem(int id);

    public void updateItem(Items items );

    public Items searchItem(String title );


}
