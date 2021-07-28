package de.neuefische.java213orderdbserver.controller;

import de.neuefische.java213orderdbserver.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void getAllProductsShouldReturnAllProducts() {

        //Given
        String url = "http://localhost:" + port + "/product";

        //When
        ResponseEntity<Product[]> response = testRestTemplate.getForEntity(url, Product[].class);

        //Then
        Product[] expected = {
                new Product("1", "piano"),
                new Product("2", "guitar"),
                new Product("3", "triangle")
        };
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertArrayEquals(expected, response.getBody());
    }

}