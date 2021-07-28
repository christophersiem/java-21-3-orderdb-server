package de.neuefische.java213orderdbserver.repository;

import de.neuefische.java213orderdbserver.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {

    private List<Product> products = List.of(
            new Product("1", "piano"),
            new Product("2", "guitar"),
            new Product("3", "triangle")
    );
    public List<Product> getAllProducts() {
         return products;
    }

    public Optional<Product> getProductById(String id) {
        for (Product product : products) {
            if(product.getId().equals(id)){
                return Optional.of(product);
            }
        } return Optional.empty();
    }
}
