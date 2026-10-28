package app.orders.service;

import app.orders.dto.OrderDto;
import app.orders.dto.OrderFindByCustomerRequest;
import app.orders.dto.OrderFindByCustomerResponse;
import app.orders.dto.OrderListResponse;
import app.orders.model.Order;
import app.system.utils.Data;

import java.util.List;

public interface OrderQueryService extends Data {

    void afisare();

    void showAllOrdersIds();

    boolean checkOrderId(int orderId);

    OrderListResponse findAllOrders();

    OrderFindByCustomerResponse findOrders(OrderFindByCustomerRequest request);

    List<OrderDto> findOrderDtosByCustomerId(int customerId);

    List<Order> findOrdersByCustomerId(int customerId);

}
