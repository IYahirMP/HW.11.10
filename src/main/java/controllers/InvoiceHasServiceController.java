package controllers;

import dao.interfaces.InvoiceHasServiceDAO;
import models.InvoiceHasService;
import views.generic.*;
import views.invoice_has_service.InvoiceHasServiceRequestData;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class InvoiceHasServiceController {
    private final InvoiceHasServiceDAO invoiceHasServiceDAO;

    public InvoiceHasServiceController(InvoiceHasServiceDAO invoiceHasServiceDAO) {
        this.invoiceHasServiceDAO = invoiceHasServiceDAO;
    }

    public int requestId(){
        RequestId req = new RequestId("InvoiceHasService");
        HashMap<String, String> patientOptions = req.getInputs();
        int id = Integer.parseInt(patientOptions.get("id"));
        return id;
    }

    public void showByInvoiceId(int invoiceId) {
        List<InvoiceHasService> record = invoiceHasServiceDAO.selectByInvoice(invoiceId);
        if(!record.isEmpty()) {
            Index<InvoiceHasService> showInvoiceHasService = new Index<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", record);
            showInvoiceHasService.setInputs(data);
            showInvoiceHasService.display();
        }
        else{
            System.out.println("InvoiceHasService not found");
        }
    }

    public void showByServiceId(int serviceId) {
        List<InvoiceHasService> record = invoiceHasServiceDAO.selectByService(serviceId);
        if(!record.isEmpty()) {
            Index<InvoiceHasService> showInvoiceHasService = new Index<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", record);
            showInvoiceHasService.setInputs(data);
            showInvoiceHasService.display();
        }
        else{
            System.out.println("InvoiceHasService not found");
        }
    }

    public void show(int invoiceId, int serviceId) {
        Optional<InvoiceHasService> record = invoiceHasServiceDAO.select(invoiceId, serviceId);
        if(record.isPresent()) {
            //Views
            Show<InvoiceHasService> showInvoiceHasService = new Show<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", record.get());
            showInvoiceHasService.setInputs(data);
            showInvoiceHasService.display();
        }
        else{
            System.out.println("InvoiceHasService not found");
        }
    }

    public void delete(int invoiceId, int serviceId) {
        int deleted = invoiceHasServiceDAO.delete(invoiceId, serviceId);
        Deleted del = new Deleted();
        HashMap<String, Object> data = new HashMap<>();
        data.put("deletedRows", deleted);
        data.put("deletedId1", invoiceId);
        data.put("deletedId2", serviceId);
        data.put("deletedType", "InvoiceHasService");
        del.setInputs(data);
        del.display();
    }

    public void insert(InvoiceHasService record) {
        int affectedRows = invoiceHasServiceDAO.insert(record);
        if(affectedRows > 0) {
            Inserted<InvoiceHasService> insertedInvoiceHasService = new Inserted<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", record);
            insertedInvoiceHasService.setInputs(data);
            insertedInvoiceHasService.display();
        }else{
            System.out.println("Insert failed");
        }
    }

    public void update(InvoiceHasService invoiceHasService) {
        int affectedRows = invoiceHasServiceDAO.update(invoiceHasService);
        if(affectedRows > 0) {
            Updated<InvoiceHasService> updatedInvoiceHasService = new Updated<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", invoiceHasService);
            data.put("updatedRows", affectedRows);
            data.put("updatedId1", invoiceHasService.getInvoiceId());
            data.put("updatedId2", invoiceHasService.getServiceId());
            updatedInvoiceHasService.setInputs(data);
            updatedInvoiceHasService.display();
        }else{
            System.out.println("Insert failed");
        }
    }

    public InvoiceHasService getData(){
        InvoiceHasServiceRequestData requestInvoiceHasServiceData = new InvoiceHasServiceRequestData();
        requestInvoiceHasServiceData.display();
        HashMap<String, String> pd = requestInvoiceHasServiceData.getInputs();

        InvoiceHasService invoiceHasService = new InvoiceHasService()
                .setInvoiceId(Integer.parseInt(pd.get("invoiceId")))
                .setServiceId(Integer.parseInt(pd.get("serviceId")))
                .setLineCost(Double.parseDouble(pd.get("lineCost")))
                .setQuantity(Integer.parseInt(pd.get("quantity")));
        return invoiceHasService;
    }

    public List<InvoiceHasService> index(){
        List<InvoiceHasService> invoiceHasServices = invoiceHasServiceDAO.selectAll();
        HashMap<String, Object> data = new HashMap<>();
        data.put("element", invoiceHasServices);
        Index<InvoiceHasService> index = new Index<>();
        index.setInputs(data);

        index.display();
        return invoiceHasServices;
    }

    public List<InvoiceHasService> selectAll(){
        return invoiceHasServiceDAO.selectAll();
    }

}
