package de.neuefische.java213orderdbserver.repository;

import de.neuefische.java213orderdbserver.model.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {

    @Test
    @DisplayName("getAllProducts should return all available products")
    public void testGetAllProducts() {

        //Given
        ProductRepository productRepository = new ProductRepository();

        //When
        List<Product> actual = productRepository.getAllProducts();

        //Then
        List<Product> expected = List.of(
                new Product("1", "piano"),
                new Product("2", "guitar"),
                new Product("3", "triangle")
        );
        assertEquals(expected, actual);
    }

}