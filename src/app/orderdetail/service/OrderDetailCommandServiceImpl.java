package app.orderdetail.service;

import app.orderdetail.dto.OrderDetailCreateRequest;
import app.orderdetail.dto.OrderDetailCreateResponse;
import app.orderdetail.dto.OrderDetailDeleteRequest;
import app.orderdetail.dto.OrderDetailDeleteResponse;
import app.orderdetail.exceptions.OrderDetailAlreadyExistsException;
import app.orderdetail.exceptions.OrderDetailNotFoundException;
import app.orderdetail.mapper.OrderDetailMapper;
import app.orderdetail.model.OrderDetail;
import app.orderdetail.repository.OrderDetailRepository;
import app.orderdetail.repository.OrderDetailRepositorySingleton;

import java.util.Optional;

public class OrderDetailCommandServiceImpl implements OrderDetailCommandService {

    private final OrderDetailRepository orderDetailRepository;
    private final OrderDetailMapper orderDetailMapper;

    public OrderDetailCommandServiceImpl() {
        orderDetailRepository = OrderDetailRepositorySingleton.getINSTANCE();
        orderDetailMapper = new OrderDetailMapper();
    }

    @Override
    public OrderDetailCreateResponse createOrderDetail(OrderDetailCreateRequest request) throws OrderDetailAlreadyExistsException {
        OrderDetail orderDetail = new OrderDetail(
                request.getOrderId(),
                request.getProductId(),
                request.getPrice(),
                request.getQuantity()
        );
        addOrderDetail(orderDetail);
        return new OrderDetailCreateResponse(orderDetailMapper.toDto(orderDetail), "Order detail created successfully");
    }

    @Override
    public void addOrderDetail(OrderDetail orderDetail) throws OrderDetailAlreadyExistsException {
        Optional<OrderDetail> searchedOrderDetail = orderDetailRepository.findOrderDetailById(orderDetail.getId());

        if (searchedOrderDetail.isPresent()) {
            throw new OrderDetailAlreadyExistsException();
        }

        orderDetailRepository.addOrderDetail(orderDetail);
    }

    @Override
    public void removeOrderDetail(OrderDetail orderDetail) throws OrderDetailNotFoundException {
        Optional<OrderDetail> searchedOrderDetail = orderDetailRepository.findOrderDetailById(orderDetail.getId());

        if (searchedOrderDetail.isEmpty()) {
            throw new OrderDetailNotFoundException();
        }

        orderDetailRepository.removeOrderDetail(orderDetail);
    }

    @Override
    public OrderDetailDeleteResponse deleteOrderDetail(OrderDetailDeleteRequest request) throws OrderDetailNotFoundException {
        OrderDetail orderDetail = orderDetailRepository.findOrderDetailById(request.getOrderDetailId())
                .orElseThrow(OrderDetailNotFoundException::new);
        removeOrderDetail(orderDetail);
        return new OrderDetailDeleteResponse(request.getOrderDetailId(), "Order detail deleted successfully");
    }
}
