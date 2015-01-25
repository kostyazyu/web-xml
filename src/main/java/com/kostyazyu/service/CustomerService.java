package com.kostyazyu.service;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import com.kostyazyu.service.entities.*;

public class CustomerService {
    List<Customer> customersList;

    public CustomerService(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(Customers.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Customers newCustomers = (Customers) unmarshaller.unmarshal(new FileReader(file));
            customersList = newCustomers.getCustomersList();
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public List<Customer> getCustomersWithOrderSumGraterThan(double orderSum) {
        List<Customer> customerList = new ArrayList<>();
        for (Customer customer : customersList) {
            if (Double.compare(getOrdersSumForCustomer(customer), orderSum) > 0) {
                customerList.add(customer);
            }
        }
        return customerList;
    }

    private double getOrdersSumForCustomer(Customer customer) {
        double allOrdersSum = 0;

        for (Order order : customer.getOrdersList()) {
            for (Position position : order.getPositions()) {
                allOrdersSum += position.getCount() * position.getPrice();
            }
        }

        return allOrdersSum;
    }



    public double getAvgOrderSum() {
        return getAllOrdersSum()/((double) getOrdersCount());
    }

    public int getOrdersCount() {
        int ordersCount = 0;
        for (Customer customer: customersList){
            for(Order order : customer.getOrdersList()){
                ordersCount++;
            }
        }
        return ordersCount;
    }

    public double getMinOrderSum() {
        double minOrderSum = Double.MAX_VALUE;
        for (Customer customer: customersList){
            for(Order order : customer.getOrdersList()){
                double currentOrderSum = 0;
                for(Position position : order.getPositions()){
                    currentOrderSum += position.getCount() * position.getPrice();
                }
                if (currentOrderSum < minOrderSum){
                    minOrderSum = currentOrderSum;
                }
            }
        }
        return minOrderSum;
    }

    public double getMaxOrderSum() {
        double maxOrderSum = 0;
        for (Customer customer: customersList){
            for(Order order : customer.getOrdersList()){
                double currentOrderSum = 0;
                for(Position position : order.getPositions()){
                    currentOrderSum += position.getCount() * position.getPrice();
                }
                if (currentOrderSum > maxOrderSum){
                    maxOrderSum = currentOrderSum;
                }
            }
        }
        return maxOrderSum;
    }

    public Customer getMaxSumCustomer() {
        Customer maxSumCustomer = null;
        double maxSum = 0;
        for (Customer customer: customersList){
            double currentSum = 0;
            for(Order order : customer.getOrdersList()){
                for(Position position : order.getPositions()){
                    currentSum += position.getCount() * position.getPrice();
                }
            }
            if (currentSum > maxSum){
                maxSum = currentSum;
                maxSumCustomer = customer;
            }
        }
        return maxSumCustomer;

    }

    public double getAllOrdersSum() {
        double allOrdersSum = 0;
        for (Customer customer: customersList){
            for(Order order : customer.getOrdersList()){
                for(Position position : order.getPositions()){
                    allOrdersSum += position.getCount() * position.getPrice();
                }
            }
        }
        return allOrdersSum;
    }

    private void printCustomers() {
        for (Customer customer : customersList){
            System.out.println("Customer name = " + customer.getName() + ", id = " + customer.getId());
            for (Order order: customer.getOrdersList()) {
                System.out.println("  Order id = " + order.getId());
                for(Position position : order.getPositions()){
                    System.out.println("    position: id = " + position.getId() + ", count = " + position.getCount() + " price = " + position.getPrice());
                }
            }
        }
    }

}
