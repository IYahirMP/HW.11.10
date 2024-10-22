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

    public InvoiceHasService setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
        return this;
    }

    public int getServiceId() {
        return serviceId;
    }

    public InvoiceHasService setServiceId(int serviceId) {
        this.serviceId = serviceId;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public InvoiceHasService setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public double getLineCost() {
        return lineCost;
    }

    public InvoiceHasService setLineCost(double lineCost) {
        this.lineCost = lineCost;
        return this;
    }
}
