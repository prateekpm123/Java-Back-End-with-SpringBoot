package org.example.productcatalogservice.repositories;

import org.example.productcatalogservice.models.Category;
import org.example.productcatalogservice.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepoTest {

    @Autowired
    public CategoryRepo categoryRepo;

    @Test
    @Transactional
    void findById() {
        Optional<Category> category = categoryRepo.findById(10L);
        for(Product p: category.get().getProduct()) {
            System.out.println(p.getName());
        }
        System.out.println(category.get().getName());
    }
}