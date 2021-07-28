package de.neuefische.java213orderdbserver.repository;

import de.neuefische.java213orderdbserver.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
