package app.orders.service;

import app.orders.dto.OrderCreateRequest;
import app.orders.dto.OrderCreateResponse;
import app.orders.dto.OrderDeleteRequest;
import app.orders.dto.OrderDeleteResponse;
import app.orders.mapper.OrderMapper;
import app.orders.exceptions.OrderAlreadyExistsException;
import app.orders.exceptions.OrderNotFoundException;
import app.orders.model.Order;
import app.orders.repository.OrderRepository;
import app.orders.repository.OrderRepositorySingleton;

import java.util.Optional;

public class OrderCommandServiceImpl implements OrderCommandService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderCommandServiceImpl() {
        orderRepository = OrderRepositorySingleton.getINSTANCE();
        orderMapper = new OrderMapper();
    }

    @Override
    public OrderCreateResponse createOrder(OrderCreateRequest request) throws OrderAlreadyExistsException {
        Order order = new Order(request.getCustomerId(), request.getAmount(), request.getShippingAddress());
        addOrder(order);
        return new OrderCreateResponse(orderMapper.toDto(order), "Order created successfully");
    }

    @Override
    public void addOrder(Order order) throws OrderAlreadyExistsException {
        Optional<Order> searchedOrder = orderRepository.findOrderById(order.getId());

        if (searchedOrder.isPresent()) {
            throw new OrderAlreadyExistsException();
        }

        orderRepository.addOrder(order);
    }

    @Override
    public void removeOrder(Order order) throws OrderNotFoundException {
        Optional<Order> searchedOrder = orderRepository.findOrderById(order.getId());

        if (searchedOrder.isEmpty()) {
            throw new OrderNotFoundException();
        }

        orderRepository.removeOrder(order);
    }

    @Override
    public OrderDeleteResponse deleteOrder(OrderDeleteRequest request) throws OrderNotFoundException {
        Order order = orderRepository.findOrderById(request.getOrderId())
                .orElseThrow(OrderNotFoundException::new);
        removeOrder(order);
        return new OrderDeleteResponse(request.getOrderId(), "Order deleted successfully");
    }
}
