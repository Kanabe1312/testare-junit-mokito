package app.orders.service;

import app.orders.dto.OrderCreateRequest;
import app.orders.dto.OrderCreateResponse;
import app.orders.dto.OrderDeleteRequest;
import app.orders.dto.OrderDeleteResponse;
import app.orders.model.Order;
import app.system.utils.Data;

public interface OrderCommandService extends Data {

    OrderCreateResponse createOrder(OrderCreateRequest request);

    void addOrder(Order order);

    OrderDeleteResponse deleteOrder(OrderDeleteRequest request);

    void removeOrder(Order order);

}
