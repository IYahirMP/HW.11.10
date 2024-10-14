package models;

public class InvoiceHasService {
    private int invoiceId;
    private int serviceId;
    private int quantity;
    private double lineCost;

    // Getters and Setters
    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getLineCost() {
        return lineCost;
    }

    public void setLineCost(double lineCost) {
        this.lineCost = lineCost;
    }
}
