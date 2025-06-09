package com.example.demo.service;


import com.example.demo.Exceptions.NotFoundException;
import com.example.demo.Repositry.ItemRepository;
import com.example.demo.entity.Items;
import com.example.demo.entity.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ServiceImp implements com.example.demo.service.Service {

    @Autowired
    private ItemRepository repository;





    public Page<Items> getItems(int page , int size){

        Pageable pageable= PageRequest.of(page, size);
        return repository.findAll(pageable);


    }

    @Override
    public String addItem(Items items) {
        items.setItemDetails(items.getItemDetails());
        repository.save(items);
        return "Item added successfully";
    }

    @Override
    public void deleteItem(int id) {
        System.out.println(id);
        if (!repository.existsById(id)) {
            System.out.println(repository.existsById(id));
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");}
        repository.deleteById(id);
    }

    @Override
    public void updateItem(Items items) {
        Optional<Items> existingItem = repository.findById(items.getId());

        if (existingItem.isPresent()) {
            Items item = existingItem.get();  // extract item
            item.setTitle(items.getTitle());
            item.setItemDetails(items.getItemDetails());
            item.setUserId(items.getUserId());
            repository.save(item);
        } else {
            throw new NotFoundException("Item not found with id: " + items.getId());
        }

    }

    @Override
    public Items searchItem(String title) {
        Optional<Items> existingItem = repository.findByTitle(title);
        Items item=new Items();
        if (existingItem.isPresent()) {
            item = existingItem.get();  // extract item

        } else {
            throw new NotFoundException("Item not found with title: " + title);
        }
        return item;


    }


}
