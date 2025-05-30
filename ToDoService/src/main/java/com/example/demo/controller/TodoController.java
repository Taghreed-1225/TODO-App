package com.example.demo.controller;


import com.example.demo.entity.Items;
import com.example.demo.service.ServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
public class TodoController {
    @Autowired
    private ServiceImp serviceImp;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addItem(@Valid @RequestBody Items items){

        serviceImp.addItem(items);
    }

    @DeleteMapping("/delete")

    public void deleteItem(@RequestParam int id){

        serviceImp.deleteItem(id);
    }

    @PutMapping("/update")

    public void updateItem(@RequestBody Items items){

        serviceImp.updateItem(items);
    }

    @GetMapping("/search")

    public Items updateItem(@RequestParam String title){

      return   serviceImp.searchItem(title);
    }





    @GetMapping("/hello")
    @ResponseStatus(HttpStatus.CREATED)
    public String hello(){

        return "Hello Taghreed";
    }

}
