package de.neuefische.java213orderdbserver.controller;

import de.neuefische.java213orderdbserver.model.Order;
import de.neuefische.java213orderdbserver.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    @Resource
    private OrderService orderService;

    public OrderController() {
    }

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order addOrder(@RequestBody List<String> productIds) {
        return orderService.addOrder(productIds);
    }

    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
