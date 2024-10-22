package models;

public class Service {
    private int serviceId;
    private String description;
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
}
