package app.orders.repository;

import app.orders.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    void loadData();

    void saveData();

    Optional<Order> findOrderById(int id);

    List<Order> listOrders();

    List<Order> findOrdersByCustomerId(int customerId);

    void addOrder(Order order);

    void removeOrder(Order order);


}
