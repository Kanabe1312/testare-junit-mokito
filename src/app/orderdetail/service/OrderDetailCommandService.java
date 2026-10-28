package app.orderdetail.service;

import app.orderdetail.dto.OrderDetailCreateRequest;
import app.orderdetail.dto.OrderDetailCreateResponse;
import app.orderdetail.dto.OrderDetailDeleteRequest;
import app.orderdetail.dto.OrderDetailDeleteResponse;
import app.orderdetail.model.OrderDetail;
import app.system.utils.Data;

public interface OrderDetailCommandService extends Data {

    OrderDetailCreateResponse createOrderDetail(OrderDetailCreateRequest request);

    void addOrderDetail(OrderDetail orderDetail);

    OrderDetailDeleteResponse deleteOrderDetail(OrderDetailDeleteRequest request);

    void removeOrderDetail(OrderDetail orderDetail);

}
