package com.kostyazyu.service.entities;

import javax.xml.bind.annotation.*;
import java.util.List;
import com.kostyazyu.service.entities.*;

@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Customer {
    private int id;
    private String name;
    @XmlElementWrapper(name = "orders")
    @XmlElement(name="order")
    private List<Order> ordersList;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Order> ordersList) {
        this.ordersList = ordersList;
    }
}
