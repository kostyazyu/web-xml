package com.kostyazyu.service.entities;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import com.kostyazyu.service.entities.*;

@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class Order {
    private int id;
    @XmlElementWrapper(name = "positions")
    @XmlElement(name="position")
    private List<Position> positions = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }
}
