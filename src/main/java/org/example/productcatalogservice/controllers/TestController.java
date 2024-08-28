package org.example.productcatalogservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController// By doing this Spring will be able to create a BEAN ( singleton object ) of this controller
public class TestController {

    @GetMapping( "/home")
    public String welcome() {
        return "This is home page.";
    }

    @GetMapping( "/")
    public String checking() {
        return "Welcome to ma world bro.";
    }
}


//Controller  -> Service -> FakeStore
// ProductDto    Product    FakeStoreDto