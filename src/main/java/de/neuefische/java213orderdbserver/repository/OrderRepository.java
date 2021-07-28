package de.neuefische.java213orderdbserver.repository;

import de.neuefische.java213orderdbserver.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {

    private final List<Order> orders = new ArrayList<>();

    public Order addOrder(Order order) {
        orders.add(order);
        return order;
    }

    public List<Order> getAllOrders(){
        return orders;
    }
}
