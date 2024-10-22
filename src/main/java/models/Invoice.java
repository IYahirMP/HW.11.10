package models;

import models.xml.LocalDateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Invoice {
    @XmlElement(required = true)
    private int invoiceId;
    
    @XmlElement(required = true)
    @XmlSchemaType(name = "decimal")
    private double total;
    
    @XmlElement(required = true)
    private int isPaid;
    
    @XmlElement(required = true)
    private int patientId;
    
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate paymentDate;

    // Getters and Setters
    public int getInvoiceId() {
        return invoiceId;
    }

    public Invoice setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
        return this;
    }

    public double getTotal() {
        return total;
    }

    public Invoice setTotal(double total) {
        this.total = total;
        return this;
    }

    public int getIsPaid() {
        return isPaid;
    }

    public Invoice setIsPaid(int isPaid) {
        this.isPaid = isPaid;
        return this;
    }

    public int getPatientId() {
        return patientId;
    }

    public Invoice setPatientId(int patientId) {
        this.patientId = patientId;
        return this;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public Invoice setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Invoice [invoiceId=")
                .append(invoiceId)
                .append(", total=")
                .append(total)
                .append(", isPaid=")
                .append(isPaid)
                .append(", patientId=")
                .append(patientId)
                .append(", paymentDate=")
                .append(paymentDate);
        return builder.toString();
    }
}
