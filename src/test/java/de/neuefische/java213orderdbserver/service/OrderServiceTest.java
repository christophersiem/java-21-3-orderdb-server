package de.neuefische.java213orderdbserver.service;

import de.neuefische.java213orderdbserver.model.Product;
import de.neuefische.java213orderdbserver.repository.OrderRepository;
import de.neuefische.java213orderdbserver.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrderServiceTest {

    @Test
    @DisplayName("addOrder should throw exception if any productId does not exist")
    public void testAddOrderWithNotMatchingProductId() {
        //Given
        OrderRepository orderRepository = new OrderRepository();
        ProductRepository productRepository = new ProductRepository();
        ProductService productService = new ProductService(productRepository);
        OrderService orderService = new OrderService(productService, orderRepository);

        try {
            //When
            orderService.addOrder(List.of("1", "4"));
            fail();
        } catch (IllegalArgumentException actual) {
            //Then
            assertEquals("Product with ID 4 does not exist", actual.getMessage());
        }
    }

    @Test
    @DisplayName("addOrder should throw exception if any productId does not exist")
    public void testAddOrderWithNotMatchingProductIdMockito() {
        //Given
        ProductService productServiceMock = mock(ProductService.class);
        when(productServiceMock.getProductById("A"))
                .thenReturn(Optional.of(new Product("A", "product id=1 of mocked service call")));

        OrderService orderService = new OrderService(productServiceMock, new OrderRepository());

        try {
            //When
            orderService.addOrder(List.of("A", "B"));
            fail();
        } catch (IllegalArgumentException actual) {
            //Then
            assertEquals("Product with ID B does not exist", actual.getMessage());
        }
    }

    @Test
    @DisplayName("addOrder should throw exception if any productId does not exist")
    public void testAddOrderWithMatchingProductShouldBePersisted() {
        //Given
        OrderRepository orderRepositoryMock = mock(OrderRepository.class);

        ProductService productServiceMock = mock(ProductService.class);
        when(productServiceMock.getProductById("A"))
                .thenReturn(Optional.of(new Product("A", "product id=1 of mocked service call")));

        OrderService orderService = new OrderService(productServiceMock, orderRepositoryMock);

        //When
        orderService.addOrder(List.of("A"));

        // THEN
        verify(orderRepositoryMock).addOrder(any());
    }
}
