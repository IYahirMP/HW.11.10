package models;

import java.sql.Date;

public class Invoice {
    private int invoiceId;
    private double total;
    private int isPaid;
    private int patientId;
    private Date paymentDate;

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

    public Date getPaymentDate() {
        return paymentDate;
    }

    public Invoice setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
        return this;
    }
}
