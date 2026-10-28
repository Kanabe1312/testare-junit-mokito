package app.orderdetail.service;

import app.orderdetail.dto.OrderDetailDto;
import app.orderdetail.dto.OrderDetailFindByOrderRequest;
import app.orderdetail.dto.OrderDetailFindByOrderResponse;
import app.orderdetail.mapper.OrderDetailMapper;
import app.orderdetail.model.OrderDetail;
import app.orderdetail.repository.OrderDetailRepository;
import app.orderdetail.repository.OrderDetailRepositorySingleton;
import app.orders.exceptions.OrderNotFoundException;

import java.util.List;
import java.util.Optional;

public class OrderDetailQueryServiceImpl implements OrderDetailQueryService {

    private final OrderDetailRepository orderDetailRepository;
    private final OrderDetailMapper orderDetailMapper;

    public OrderDetailQueryServiceImpl() {
        orderDetailRepository = OrderDetailRepositorySingleton.getINSTANCE();
        orderDetailMapper = new OrderDetailMapper();
    }


    @Override
    public void afisare() {

        orderDetailRepository.listOrderDetails().forEach(System.out::println);
    }

    @Override
    public OrderDetailFindByOrderResponse findOrderDetails(OrderDetailFindByOrderRequest request) throws OrderNotFoundException {
        return new OrderDetailFindByOrderResponse(findOrderDetailDtosByOrderId(request.getOrderId()));
    }

    @Override
    public List<OrderDetailDto> findOrderDetailDtosByOrderId(int orderId) throws OrderNotFoundException {
        return findOrderDetailsByOrderId(orderId).stream()
                .map(orderDetailMapper::toDto)
                .toList();
    }

    @Override
    public List<OrderDetail> findOrderDetailsByOrderId(int orderId) throws OrderNotFoundException {

        Optional<OrderDetail> searchedOrderDetail = orderDetailRepository.listOrderDetails().stream().filter(orderDetail -> orderDetail.getOrderId() == orderId).findAny();

        if(searchedOrderDetail.isEmpty()){
            throw new OrderNotFoundException();
        } else {
            return orderDetailRepository.findOrderDetailsByOrderId(orderId);
        }
    }


}
