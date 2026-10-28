package app.orderdetail.repository;

import app.orderdetail.model.OrderDetail;
import app.system.utils.DataPaths;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class OrderDetailRepositoryImpl implements OrderDetailRepository{
    private List<OrderDetail> orderdetails;
    private String filename;

    public OrderDetailRepositoryImpl() {
        orderdetails = new ArrayList<>();

        this.filename = DataPaths.of("src", "app", "orderdetail", "data", "OrderDetails.txt");
        this.loadData();
    }

    @Override
    public void loadData() {
        File file = new File(filename);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                OrderDetail orderDetail = new OrderDetail(line);
                orderdetails.add(orderDetail);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveData() {

        File file = new File(filename);
        try {
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(this);
            printWriter.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OrderDetail> listOrderDetails() {

        return orderdetails;
    }

    @Override
    public List<OrderDetail> findOrderDetailsByOrderId(int orderId) {

        return orderdetails.stream().filter(orderDetail -> orderDetail.getOrderId() == orderId).collect(Collectors.toList());
    }

    @Override
    public Optional<OrderDetail> findOrderDetailById(int id) {

        return orderdetails.stream().filter(orderDetail -> orderDetail.getId() == id).findAny();
    }

    @Override
    public void addOrderDetail(OrderDetail orderDetail) {
        orderDetail.setId(generateOrderDetailId());
        orderdetails.add(orderDetail);
        this.saveData();;
    }

    @Override
    public void removeOrderDetail(OrderDetail orderDetail) {
        orderdetails.remove(orderDetail);
        this.saveData();
    }


    private int generateOrderDetailId() {
        Random rand = new Random();
        int orderDetailId = rand.nextInt(10000)+1;
        while(findOrderDetailById(orderDetailId).isPresent()) {
            orderDetailId = rand.nextInt(10000)+1;
        }

        return orderDetailId;
    }

    @Override
    public String toString() {

        StringBuilder text;
        text = new StringBuilder();
        int i;
        for( i=0 ;i<orderdetails.size()-1; i++){
            text.append(orderdetails.get(i)+"\n");
        }
        text.append(orderdetails.get(i));
        return String.valueOf(text);
    }



}
