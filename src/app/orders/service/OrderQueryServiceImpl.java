package app.orders.service;

import app.orders.exceptions.CustomerNotFoundException;
import app.orders.dto.OrderDto;
import app.orders.dto.OrderFindByCustomerRequest;
import app.orders.dto.OrderFindByCustomerResponse;
import app.orders.dto.OrderListResponse;
import app.orders.mapper.OrderMapper;
import app.orders.model.Order;
import app.orders.repository.OrderRepository;
import app.orders.repository.OrderRepositorySingleton;

import java.util.*;

public class OrderQueryServiceImpl implements OrderQueryService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderQueryServiceImpl() {
        orderRepository = OrderRepositorySingleton.getINSTANCE();
        orderMapper = new OrderMapper();
    }


    @Override
    public boolean checkOrderId(int orderId){
        return orderRepository.listOrders().stream().anyMatch(order -> order.getId() == orderId);

    }

    @Override
    public OrderListResponse findAllOrders() {
        return new OrderListResponse(
                orderRepository.listOrders().stream()
                        .map(orderMapper::toDto)
                        .toList()
        );
    }

    @Override
    public OrderFindByCustomerResponse findOrders(OrderFindByCustomerRequest request) throws CustomerNotFoundException {
        return new OrderFindByCustomerResponse(findOrderDtosByCustomerId(request.getCustomerId()));
    }

    @Override
    public List<OrderDto> findOrderDtosByCustomerId(int customerId) throws CustomerNotFoundException {
        return findOrdersByCustomerId(customerId).stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public List<Order> findOrdersByCustomerId(int customerId) throws CustomerNotFoundException {
        List<Order> customerOrders = orderRepository.findOrdersByCustomerId(customerId);
        if(customerOrders.isEmpty()){
            throw new CustomerNotFoundException();
        }
        return customerOrders;
    }

    @Override
    public void afisare() {

        orderRepository.listOrders().forEach(System.out::println);
    }

    @Override
    public void showAllOrdersIds(){

        orderRepository.listOrders().forEach(order -> System.out.println(order.getId()));
    }






}
