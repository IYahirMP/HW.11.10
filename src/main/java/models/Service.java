package models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Service {
    @XmlElement(required = true)
    private int serviceId;
    @XmlElement(required = true)
    private String description;
    @XmlElement(required = true)
    private double cost;

    // Getters and Setters
    public int getServiceId() {
        return serviceId;
    }

    public Service setServiceId(int serviceId) {
        this.serviceId = serviceId;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Service setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getCost() {
        return cost;
    }

    public Service setCost(double cost) {
        this.cost = cost;
        return this;
    }

    @Override
    public String toString() {
        String builder = "Service[" +
                "serviceId=" + serviceId +
                ", description=" + description +
                ", cost=" + cost +
                "]";
        return builder;
    }
}
