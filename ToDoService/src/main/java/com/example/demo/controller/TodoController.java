package com.example.demo.controller;


import com.example.demo.entity.Items;
import com.example.demo.service.ServiceImp;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
@OpenAPIDefinition(
        info = @Info(
                title = "TODO API",
                version = "1.0",
                description = "Handle operations ^cruds^ on items"
        )
)
@RestController
@RequestMapping("/items")

public class TodoController {
    @Autowired
    private ServiceImp serviceImp;

    @Autowired
    private RestTemplate restTemplate;


    private static final String USER_SERVICE_URL = "http://localhost:8080/validateToken";


    @Operation(summary = "Add new item and its details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED, item inserted"),
            @ApiResponse(responseCode = "401", description = "Invalid token")
    })


    @PostMapping
    public  ResponseEntity<String>  addItem(@Valid @RequestBody Items items ,  @RequestHeader("Authorization") String token ,  BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Validation failed: " + bindingResult.getAllErrors().get(0).getDefaultMessage(),
                    HttpStatus.BAD_REQUEST);
        }

        if (!isTokenValid(token)) {
            return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(serviceImp.addItem(items), HttpStatus.CREATED);

    }


    @Operation(summary = "Delete item by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item deleted"),
            @ApiResponse(responseCode = "401", description = "Invalid token"),
            @ApiResponse(responseCode = "404", description = "Not found, no item with this id")
    })


    @DeleteMapping

    public ResponseEntity<String>  deleteItem(@RequestParam int id , @RequestHeader("Authorization") String token){

        if (!isTokenValid(token)) {

            return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);

        }

        serviceImp.deleteItem(id);
        return new ResponseEntity<>("Item deleted successfully", HttpStatus.OK);

    }


    @Operation(summary = "Update an item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item updated"),
            @ApiResponse(responseCode = "401", description = "Invalid token"),
            @ApiResponse(responseCode = "404", description = "Not found, no item with this id")
    })
    @PutMapping

    public ResponseEntity<String> updateItem(@RequestBody Items items , @RequestHeader("Authorization") String token){
        if (!isTokenValid(token)) {

            return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);

        }

        serviceImp.updateItem(items);
        return new ResponseEntity<>("Item updated successfully", HttpStatus.OK);
    }
    @Operation(summary = "Search for an item by title")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item found"),
            @ApiResponse(responseCode = "401", description = "Invalid token"),
            @ApiResponse(responseCode = "404", description = "Not found, no item with this title")
    })
    @GetMapping

    public  ResponseEntity<?>  searcheItem(@RequestParam String title , @RequestHeader("Authorization") String token){
        if (!isTokenValid(token)) {

            return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);

        }
        Items item = serviceImp.searchItem(title);
        return ResponseEntity.ok(item);
    }


    @Operation(summary = "Simple hello endpoint (testing)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Greeting returned")
    })

    @GetMapping("/hello")
    @ResponseStatus(HttpStatus.CREATED)
    public String hello(){

        return "Hello Taghreed";
    }

    public boolean isTokenValid(String token) {
        System.out.println("is token valid in todo controller");
        HttpHeaders headers = new HttpHeaders();
        System.out.println("1");
        headers.set("Authorization", token);
        System.out.println("2");// أو Bearer + token لو مطلوب
        HttpEntity<String> entity = new HttpEntity<>(headers);
        System.out.println("3");

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    USER_SERVICE_URL,
                    HttpMethod.POST,
                    entity,
                    String.class


            );
            System.out.println("4");
            System.out.println("Response body: " + response.getBody());

            return "valid token".equalsIgnoreCase(response.getBody());
        }

        catch (HttpClientErrorException | HttpServerErrorException e) {
            System.out.println("5");
            return false;
        }
    }

}
