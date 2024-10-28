package models.xml;

import models.InvoiceHasService;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "InvoiceHasServices")
public class InvoiceHasServices {

    private List<InvoiceHasService> invoiceHasServices;

    @XmlElement(name = "InvoiceHasService")
    public List<InvoiceHasService> getInvoiceHasServices() {
        return invoiceHasServices;
    }

    public InvoiceHasServices setInvoiceHasServices(List<InvoiceHasService> invoiceHasServices) {
        this.invoiceHasServices = invoiceHasServices;
        return this;
    }
}
