package de.neuefische.java213orderdbserver.controller;

import com.google.gson.Gson;
import de.neuefische.java213orderdbserver.model.Product;
import de.neuefische.java213orderdbserver.repository.OrderRepository;
import de.neuefische.java213orderdbserver.service.OrderService;
import de.neuefische.java213orderdbserver.service.ProductService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // this test requires a strict order
@WebMvcTest(OrderController.class)
public class OrderControllerMockMVCTest {

    @MockBean
    ProductService productService;
    @Resource
    private MockMvc mockMvc;
    @SpyBean()
    private OrderService orderServiceMock;
    @SpyBean
    private OrderRepository orderRepository;

    @Order(1)
    @Test
    public void testOrdersGetEmpty() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/order/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)))
        ;
    }

    @Order(2)
    @Test
    public void testOrderPost() throws Exception {
        when(productService.getProductById("1")).thenReturn(
                Optional.of(new Product("1", "piano"))
        );

        // mock order id generation
        doReturn("123").when(orderServiceMock).generateOrderId();

        Gson gson = new Gson();
        String jsonProductIdJson = gson.toJson(Collections.singleton("1"));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/order")
                        .content(jsonProductIdJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("123")))
                .andExpect(jsonPath("$.products[0].name", is("piano")))
        ;
    }

    @Order(3)
    @Test
    public void testOrdersGet() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/order/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", is("123")))
                .andExpect(jsonPath("$.[0].products[0].name", is("piano")))
        ;
    }
}
