package de.neuefische.java213orderdbserver.repository;

import de.neuefische.java213orderdbserver.model.Order;
import de.neuefische.java213orderdbserver.model.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderRepositoryTest {

    @Test
    @DisplayName("addOrder should add order to orderlist")
    public void testAddOrder() {

        //Given
        OrderRepository orderRepository = new OrderRepository();
        Order order = new Order("1", List.of(
                new Product("1", "piano"),
                new Product("2", "guitar")
        ));

        //When
        orderRepository.addOrder(order);

        //Then
        assertTrue(orderRepository.getAllOrders().contains(new Order("1", List.of(
                new Product("1", "piano"),
                new Product("2", "guitar")
        ))));
    }

}