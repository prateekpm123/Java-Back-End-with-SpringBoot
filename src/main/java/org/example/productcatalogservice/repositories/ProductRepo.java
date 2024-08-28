package org.example.productcatalogservice.repositories;

import org.example.productcatalogservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    /*
    ## Question
    in a basic java spring boot aplication with an orm using JPA, we create a repo ProductRepo interface that extends JPARepository. Now how is it that we are able to use that ProductRepo with it's functions, even it's just an interface ?

    In a Spring Boot application using JPA, when you create a repository interface like `ProductRepo` that extends `JpaRepository`, Spring Data JPA automatically provides an implementation for that interface at runtime. This is part of the magic of Spring Data, and it's possible because of Spring's use of proxies and dependency injection.

Here's how it works step by step:

### 1. **Defining the Repository Interface**
   - You define a repository interface, `ProductRepo`, which extends `JpaRepository`. This interface doesn't need any method implementations; you just declare it.

   ```java
   import org.springframework.data.jpa.repository.JpaRepository;

   public interface ProductRepo extends JpaRepository<Product, Long> {
       // No need to implement anything
   }
   ```

### 2. **Spring Data JPA's Role**
   - Spring Data JPA recognizes your repository interface during the component scan (enabled by `@SpringBootApplication` or `@EnableJpaRepositories`).
   - It sees that `ProductRepo` extends `JpaRepository`, so it knows that this interface represents a repository that should have CRUD (Create, Read, Update, Delete) operations, among other JPA-related methods.

### 3. **Automatic Implementation**
   - At runtime, Spring Data JPA automatically creates a proxy object (an implementation) for `ProductRepo`.
   - This proxy object is created using Java's dynamic proxy or CGLIB (Code Generation Library), depending on whether the interface contains methods that are not part of the `JpaRepository`.

### 4. **Dependency Injection**
   - When you inject `ProductRepo` into a service or controller using `@Autowired`, `@Inject`, or constructor injection, Spring injects the proxy object (the actual implementation of your `ProductRepo`).
   - This proxy object contains implementations of all the methods declared in `JpaRepository`, such as `save()`, `findAll()`, `findById()`, etc.

   ```java
   @Service
   public class ProductService {

       private final ProductRepo productRepo;

       @Autowired
       public ProductService(ProductRepo productRepo) {
           this.productRepo = productRepo;
       }

       public List<Product> getAllProducts() {
           return productRepo.findAll();
       }
   }
   ```

### 5. **Using the Repository**
   - Once injected, you can use `productRepo` as if it were an actual class with fully implemented methods.
   - For example, `productRepo.findAll()` will fetch all `Product` entities from the database.

### **How is this Possible?**

This behavior is possible because of the following Spring mechanisms:

1. **Proxy Pattern**: Spring uses the proxy pattern to wrap your repository interface with a dynamically generated class that implements all the methods of `JpaRepository`.
2. **Dependency Injection**: Spring’s dependency injection mechanism ensures that the correct proxy object is injected wherever your repository is needed.
3. **Spring Data JPA**: It takes care of generating the appropriate SQL queries, managing transactions, and handling database interactions based on the method names and annotations in your repository.

### **Conclusion**

Even though `ProductRepo` is just an interface, Spring Data JPA automatically provides the implementation at runtime. This allows you to work with your repository interface as if it were a fully implemented class, complete with all necessary CRUD and query methods.
     */

}
