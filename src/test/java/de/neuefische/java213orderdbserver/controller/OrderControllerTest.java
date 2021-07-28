package de.neuefische.java213orderdbserver.controller;

import de.neuefische.java213orderdbserver.model.Order;
import de.neuefische.java213orderdbserver.model.Product;
import de.neuefische.java213orderdbserver.repository.OrderRepository;
import de.neuefische.java213orderdbserver.service.OrderService;
import de.neuefische.java213orderdbserver.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class OrderControllerTest {

    /**
     * SpringBootTest.WebEnvironment.DEFINED_PORT use always default port 8080
     */
    private final static String BASE_URL = "http://localhost:8080";

    @Resource
    private TestRestTemplate testRestTemplate;

    @Resource
    private OrderController orderController;

    @Spy
    private OrderService orderService;

    @Resource
    private ProductService productService;

    @Resource
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        orderService.setProductService(productService);
        orderService.setOrderRepository(orderRepository);

        orderController.setOrderService(orderService);
    }

    @Test
    public void testRest() {
        Order createdOrder = createAnOrder();

        fetchingAllOrders(createdOrder);
    }

    private Order createAnOrder() {
        // orderService will be forced to return always 1 when calling generateOrderId
        doReturn("1").when(orderService).generateOrderId();

        String addOrderUrl = String.format("%s/order", BASE_URL);
        ResponseEntity<Order> orderAddedEntity = testRestTemplate.postForEntity(addOrderUrl, List.of("1"), Order.class);

        assertEquals(HttpStatus.OK, orderAddedEntity.getStatusCode());

        Order expectedOrder = new Order("1", List.of(
                new Product("1", "piano")
        ));
        assertEquals(expectedOrder, orderAddedEntity.getBody());

        return expectedOrder;
    }

    private void fetchingAllOrders(Order expectedOrder) {
        String getOrdersUrl = String.format("%s/order/all", BASE_URL);
        ResponseEntity<Order[]> allOrdersEntity = testRestTemplate.getForEntity(getOrdersUrl, Order[].class);

        assertEquals(HttpStatus.OK, allOrdersEntity.getStatusCode());

        Order[] expectedOrdersArray = {
                expectedOrder
        };
        assertArrayEquals(expectedOrdersArray, allOrdersEntity.getBody());
    }
}

