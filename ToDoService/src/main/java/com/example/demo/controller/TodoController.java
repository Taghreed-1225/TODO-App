package com.example.demo.controller;


import com.example.demo.config.RestTemplateConfig;
import com.example.demo.entity.Items;
import com.example.demo.service.ServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;


@RestController
public class TodoController {
    @Autowired
    private ServiceImp serviceImp;

    private final RestTemplate restTemplate;

    private static final String USER_SERVICE_URL = "http://localhost:8081/app/auth/validation";

    public TodoController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    private boolean checkTokenValidity(String token) {
        ResponseEntity<String> response = restTemplate.postForEntity(USER_SERVICE_URL, token, String.class);
        return response.getStatusCode().is2xxSuccessful();
    }

    @Operation(summary = "Add new item and its details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED, item inserted")
    })

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addItem(@Valid @RequestBody Items items){

        serviceImp.addItem(items);
    }

    @Operation(summary = "Delete item using ID ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK, Item deleted"),
            @ApiResponse(responseCode = "404", description = "Not found, no item with this id")
    })
    @DeleteMapping("/delete")

    public void deleteItem(@RequestParam int id){

        serviceImp.deleteItem(id);
    }

    @Operation(summary = "Update item data and its details by using ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK, Item updated"),
            @ApiResponse(responseCode = "404", description = "Not found, no item with this id")
    })
    @PutMapping("/update")

    public void updateItem(@RequestBody Items items){

        serviceImp.updateItem(items);
    }

    @GetMapping("/search")

    public Items searcheItem(@RequestParam String title){

      return   serviceImp.searchItem(title);
    }





    @GetMapping("/hello")
    @ResponseStatus(HttpStatus.CREATED)
    public String hello(){

        return "Hello Taghreed";
    }

}
