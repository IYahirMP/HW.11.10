package controllers;

import dao.InvoiceHasServiceDAO;
import models.InvoiceHasService;
import views.generic.*;

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

    public void show(int id) {
        Optional<InvoiceHasService> record = invoiceHasServiceDAO.select(id);
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

    public void delete(int id) {
        int deleted = invoiceHasServiceDAO.delete(id);
        Deleted del = new Deleted();
        HashMap<String, Object> data = new HashMap<>();
        data.put("deletedRows", deleted);
        data.put("deletedId", id);
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

    public void update(int id, InvoiceHasService consultation) {
        int affectedRows = invoiceHasServiceDAO.update(id, consultation);
        if(affectedRows > 0) {
            Updated<InvoiceHasService> updatedInvoiceHasService = new Updated<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", consultation);
            data.put("updatedRows", affectedRows);
            data.put("updatedId", id);
            updatedInvoiceHasService.setInputs(data);
            updatedInvoiceHasService.display();
        }else{
            System.out.println("Insert failed");
        }
    }

    public InvoiceHasService getData(){/*
        InvoiceHasServiceRequestData requestInvoiceHasServiceData = new InvoiceHasServiceRequestData();
        requestInvoiceHasServiceData.display();
        HashMap<String, String> pd = requestInvoiceHasServiceData.getInputs();

        InvoiceHasService consultation = new InvoiceHasService()
                .setPatientId(Integer.parseInt(pd.get("patientId")))
                .setInvoiceHasServiceId(Integer.parseInt(pd.get("consultationId")))
                .setAdmissionDate(Date.valueOf(pd.get("admissionDate")))
                .setDischargeDate(Date.valueOf(pd.get("dischargeDate")))
                .setRoomNumber(Integer.parseInt(pd.get("roomNumber")))
                .setBedNumber(Integer.parseInt(pd.get("bedNumber")));
        return consultation;*/
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void index(){
        List<InvoiceHasService> patients = invoiceHasServiceDAO.selectAll();
        HashMap<String, Object> data = new HashMap<>();
        data.put("element", patients);
        Index<InvoiceHasService> index = new Index<>();
        index.setInputs(data);

        index.display();

        /*System.out.println("Do you want to export it to XML?");
        Scanner sc = new Scanner(System.in);
        int answer = sc.nextInt();
        if(answer == 1) {
            InvoiceHasServices patientList = new InvoiceHasServices();
            patientList.setInvoiceHasServices(patients);
            exportXML("InvoiceHasServiceList.xml", patientList);
        }else{
            System.out.println("Going back...");
        }*/
    }

}
