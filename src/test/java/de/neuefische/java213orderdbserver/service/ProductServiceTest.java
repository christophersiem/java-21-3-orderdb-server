package de.neuefische.java213orderdbserver.service;

import de.neuefische.java213orderdbserver.model.Product;
import de.neuefische.java213orderdbserver.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    @Test
    @DisplayName("search should return all Products with matching string")
    public void testSearch() {

        //Given
        ProductRepository productRepository = new ProductRepository();
        ProductService productService = new ProductService(productRepository);

        //When
        List<Product> actual = productService.search("pi");

        //Then
        List<Product> expected = List.of(new Product("1", "piano"));
        assertEquals(expected, actual);


    }

}