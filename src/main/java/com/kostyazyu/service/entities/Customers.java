package com.kostyazyu.service.entities;


import javax.xml.bind.annotation.*;
import java.util.List;
import com.kostyazyu.service.entities.*;

//@XmlRootElement(namespace = "com.kostyazyu.xml.jaxb.model")
@XmlRootElement(name="customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class Customers {

    //@XmlElementWrapper(name = "customersList")
    @XmlElement(name="customer")
    private List<Customer> customersList;

    public List<Customer> getCustomersList() {
        return customersList;
    }

    public void setCustomersList(List<Customer> customersList) {
        this.customersList = customersList;
    }
}
