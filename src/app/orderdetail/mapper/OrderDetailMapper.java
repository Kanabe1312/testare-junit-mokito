package app.orderdetail.mapper;

import app.orderdetail.dto.OrderDetailDto;
import app.orderdetail.model.OrderDetail;

public class OrderDetailMapper {

    public OrderDetailDto toDto(OrderDetail orderDetail) {
        return new OrderDetailDto(
                orderDetail.getId(),
                orderDetail.getOrderId(),
                orderDetail.getProductId(),
                orderDetail.getPrice(),
                orderDetail.getQuantity()
        );
    }

    public OrderDetail toModel(OrderDetailDto orderDetailDto) {
        return new OrderDetail(
                orderDetailDto.getId(),
                orderDetailDto.getOrderId(),
                orderDetailDto.getProductId(),
                orderDetailDto.getPrice(),
                orderDetailDto.getQuantity()
        );
    }
}
