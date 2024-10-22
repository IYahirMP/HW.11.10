package models;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class InvoiceHasService {
    @XmlElement(required = true)
    private int invoiceId;

    @XmlElement(required = true)
    private int serviceId;

    @XmlElement(required = true)
    private int quantity;

    @XmlElement(required = true)
    @XmlSchemaType(name = "decimal")
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("InvoiceHasService{")
                .append("invoiceId=").append(invoiceId)
                .append(", serviceId=").append(serviceId)
                .append(", quantity=").append(quantity)
                .append(", lineCost=").append(lineCost)
                .append("}");
        return sb.toString();
    }
}
