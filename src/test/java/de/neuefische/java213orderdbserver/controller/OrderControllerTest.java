package de.neuefische.java213orderdbserver.controller;

import de.neuefische.java213orderdbserver.model.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class OrderControllerTest {

    @Resource
    private TestRestTemplate testRestTemplate;

    /**
     * SpringBootTest.WebEnvironment.DEFINED_PORT use always default port 8080
     */
    private final static String BASE_URL = "http://localhost:8080";

    @Test
    public void testRest() {
        String url = String.format("%s/order/all", BASE_URL);
        ResponseEntity<Order[]> orderResponseEntity = testRestTemplate.getForEntity(url, Order[].class);

        assertEquals(HttpStatus.OK, orderResponseEntity.getStatusCode());

        Order[] orderArray = orderResponseEntity.getBody();
        assertNotNull(orderArray);
        assertEquals(0, orderArray.length);
    }
}

