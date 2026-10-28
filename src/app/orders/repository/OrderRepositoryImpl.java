package app.orders.repository;

import app.orderdetail.model.OrderDetail;
import app.orders.model.Order;
import app.system.utils.DataPaths;

import java.io.*;
import java.util.*;

public class OrderRepositoryImpl implements OrderRepository{

    private List<Order> orders;
    String filename;

    public OrderRepositoryImpl() {
        orders = new ArrayList<>();

        this.filename = DataPaths.of("src", "app", "orders", "data", "Orders.txt");
        this.loadData();
    }

    @Override
    public void loadData() {
        File file = new File(filename);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Order order = new Order(line);
                orders.add(order);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveData() {
        File file = new File(filename);

        try{
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(this);
            printWriter.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Order> findOrderById(int orderId) {

        return orders.stream().filter(order -> order.getId() == orderId).findAny();
    }

    @Override
    public List<Order> listOrders() {

        return orders.stream().toList();
    }

    @Override
    public List<Order> findOrdersByCustomerId(int customerId) {
        return orders.stream()
                .filter(order -> order.getCustomerId() == customerId)
                .toList();
    }

    @Override
    public void addOrder(Order order) {
        order.setId(generateUniqueOrderId());
        orders.add(order);
        this.saveData();
    }

    @Override
    public void removeOrder(Order order) {
        orders.remove(order);
        this.saveData();
    }

    private int generateUniqueOrderId() {

        Random rand = new Random();
        int orderID = rand.nextInt(10000)+1;
        while(findOrderById(orderID).isPresent()) {
            orderID = rand.nextInt(10000)+1;
        }

        return orderID;
    }

    @Override
    public String toString() {

        StringBuilder text;
        text = new StringBuilder();
        int i;
        for( i=0 ;i<orders.size()-1; i++){
            text.append(orders.get(i)+"\n");
        }
        text.append(orders.get(i));
        return String.valueOf(text);
    }

}
