package de.neuefische.java213orderdbserver.service;

import de.neuefische.java213orderdbserver.model.Order;
import de.neuefische.java213orderdbserver.model.Product;
import de.neuefische.java213orderdbserver.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final ProductService productService;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(ProductService productService, OrderRepository orderRepository) {
        this.productService = productService;
        this.orderRepository = orderRepository;
    }

    public Order addOrder(List<String> productIds) {
        List<Product> productsToOrder = new ArrayList<>();
        for (String productId : productIds) {
            Product product = productService.getProductById(productId)
                    .orElseThrow(() -> new IllegalArgumentException("Product with ID " + productId + " does not exist"));
            productsToOrder.add(product);
        }
        String id = UUID.randomUUID().toString();
        Order order = new Order(id, productsToOrder);
        return orderRepository.addOrder(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }
}
