package app.orders.mapper;

import app.orders.dto.OrderDto;
import app.orders.model.Order;

public class OrderMapper {

    public OrderDto toDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getCustomerId(),
                order.getAmmount(),
                order.getShippingAddres()
        );
    }

    public Order toModel(OrderDto orderDto) {
        return new Order(
                orderDto.getId(),
                orderDto.getCustomerId(),
                orderDto.getAmount(),
                orderDto.getShippingAddress()
        );
    }
}
