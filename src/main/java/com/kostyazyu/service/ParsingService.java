package com.kostyazyu.service;


import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import com.kostyazyu.service.entities.*;

public class ParsingService {
    private static File file;
    List<Customer> customersList;

    public ParsingService(File file) {
        try {
            this.file = file;
            JAXBContext context = JAXBContext.newInstance(Customers.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Customers newCustomers = (Customers) unmarshaller.unmarshal(new FileReader(file));
            customersList = newCustomers.getCustomersList();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void schemaValidation() throws IOException {
        // 1. Lookup a factory for the W3C XML Schema language
        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");

        // 2. Compile the schema.
        // Here the schema is loaded from a java.io.File, but you could use
        // a java.net.URL or a javax.xml.transform.Source instead.
        File schemaLocation = new File("C:\\Projects\\xml\\customers.xsd");
        try {
            Schema schema = factory.newSchema(schemaLocation);
            // 3. Get a validator from the schema.
            Validator validator = schema.newValidator();
            // 4. Parse the document you want to check.
            Source source = new StreamSource(file);
            // 5. Check the document
            validator.validate(source);
            System.out.println(file.getName() + " is valid.");
        }
        catch (SAXException ex) {
            System.out.println(file.getName() + " is not valid because ");
            System.out.println(ex.getMessage());
        }

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

    public void printCustomers() {
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
