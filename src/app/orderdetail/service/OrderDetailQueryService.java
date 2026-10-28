package app.orderdetail.service;

import app.orderdetail.dto.OrderDetailDto;
import app.orderdetail.dto.OrderDetailFindByOrderRequest;
import app.orderdetail.dto.OrderDetailFindByOrderResponse;
import app.orderdetail.model.OrderDetail;

import java.util.List;

public interface OrderDetailQueryService  {

    void afisare();

    OrderDetailFindByOrderResponse findOrderDetails(OrderDetailFindByOrderRequest request);

    List<OrderDetailDto> findOrderDetailDtosByOrderId(int orderId);

    List<OrderDetail> findOrderDetailsByOrderId(int orderId);

}
