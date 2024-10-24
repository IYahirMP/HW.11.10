package models.xml;

import models.Invoice;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Invoices")
public class Invoices {

    private List<Invoice> invoices;

    @XmlElement(name = "Invoice")
    public List<Invoice> getInvoices() {
        return invoices;
    }

    public Invoices setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
        return this;
    }
}
