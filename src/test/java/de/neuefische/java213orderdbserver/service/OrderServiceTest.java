package de.neuefische.java213orderdbserver.service;

import de.neuefische.java213orderdbserver.repository.OrderRepository;
import de.neuefische.java213orderdbserver.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    @Test
    @DisplayName("addOrder should throw exception if any productId does not exist")
    public void testAddOrderWithNotMatchingProductId(){

        //Given
        OrderRepository orderRepository = new OrderRepository();
        ProductRepository productRepository = new ProductRepository();
        ProductService productService = new ProductService(productRepository);
        OrderService orderService = new OrderService(productService, orderRepository);

        try{
            //When
            orderService.addOrder(List.of("1","4"));
            fail();
        } catch (IllegalArgumentException actual){
            //Then
            assertEquals("Product with ID 4 does not exist", actual.getMessage());
        }
    }



}