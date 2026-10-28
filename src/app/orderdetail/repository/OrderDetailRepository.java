package app.orderdetail.repository;

import app.orderdetail.model.OrderDetail;

import java.util.List;
import java.util.Optional;

public interface OrderDetailRepository {

    void loadData();

    void saveData();

    List<OrderDetail> listOrderDetails();

    List<OrderDetail> findOrderDetailsByOrderId(int orderId);

    Optional<OrderDetail> findOrderDetailById(int id);

    void addOrderDetail(OrderDetail orderDetail);

    void removeOrderDetail(OrderDetail orderDetail);
}
