package controllers;

import dao.interfaces.InvoiceDAO;
import models.Invoice;
import views.generic.*;
import views.invoice.InvoiceRequestData;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class InvoiceController {
    private final InvoiceDAO invoiceDAO;

    public InvoiceController(InvoiceDAO invoiceDAO) {
        this.invoiceDAO = invoiceDAO;
    }

    public int requestId(){
        RequestId req = new RequestId("Invoice");
        HashMap<String, String> patientOptions = req.getInputs();
        int id = Integer.parseInt(patientOptions.get("id"));
        return id;
    }

    public void show(int id) {
        Optional<Invoice> record = invoiceDAO.select(id);
        if(record.isPresent()) {
            //Views
            Show<Invoice> showInvoice = new Show<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", record.get());
            showInvoice.setInputs(data);
            showInvoice.display();
        }
        else{
            System.out.println("Invoice not found");
        }
    }

    public void delete(int id) {
        int deleted = invoiceDAO.delete(id);
        Deleted del = new Deleted();
        HashMap<String, Object> data = new HashMap<>();
        data.put("deletedRows", deleted);
        data.put("deletedId", id);
        data.put("deletedType", "Invoice");
        del.setInputs(data);
        del.display();
    }

    public void insert(Invoice record) {
        int affectedRows = invoiceDAO.insert(record);
        if(affectedRows > 0) {
            Inserted<Invoice> insertedInvoice = new Inserted<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", record);
            insertedInvoice.setInputs(data);
            insertedInvoice.display();
        }else{
            System.out.println("Insert failed");
        }
    }

    public void update(int id, Invoice invoice) {
        int affectedRows = invoiceDAO.update(id, invoice);
        if(affectedRows > 0) {
            Updated<Invoice> updatedInvoice = new Updated<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", invoice);
            data.put("updatedRows", affectedRows);
            data.put("updatedId", id);
            updatedInvoice.setInputs(data);
            updatedInvoice.display();
        }else{
            System.out.println("Insert failed");
        }
    }

    public Invoice getData(){
        InvoiceRequestData requestInvoiceData = new InvoiceRequestData();
        requestInvoiceData.display();
        HashMap<String, String> pd = requestInvoiceData.getInputs();

        Invoice invoice = new Invoice()
                .setPatientId(Integer.parseInt(pd.get("patientId")))
                .setIsPaid(Integer.parseInt(pd.get("isPaid")))
                .setTotal(Double.parseDouble(pd.get("total")))
                .setPaymentDate(LocalDate.parse(pd.get("paymentDate")));
        return invoice;
    }

    public List<Invoice> index(){
        List<Invoice> invoices = invoiceDAO.selectAll();
        HashMap<String, Object> data = new HashMap<>();
        data.put("element", invoices);
        Index<Invoice> index = new Index<>();
        index.setInputs(data);

        index.display();
        return invoices;
    }

    public List<Invoice> selectAll(){
        return invoiceDAO.selectAll();
    }

}
