package controllers;

import dao.ServiceDAO;
import models.Service;
import views.generic.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class ServiceController {
    private final ServiceDAO serviceDAO;

    public ServiceController(ServiceDAO serviceDAO) {
        this.serviceDAO = serviceDAO;
    }

    public int requestId(){
        RequestId req = new RequestId("Service");
        HashMap<String, String> patientOptions = req.getInputs();
        int id = Integer.parseInt(patientOptions.get("id"));
        return id;
    }

    public void show(int id) {
        Optional<Service> record = serviceDAO.select(id);
        if(record.isPresent()) {
            //Views
            Show<Service> showService = new Show<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", record.get());
            showService.setInputs(data);
            showService.display();
        }
        else{
            System.out.println("Service not found");
        }
    }

    public void delete(int id) {
        int deleted = serviceDAO.delete(id);
        Deleted del = new Deleted();
        HashMap<String, Object> data = new HashMap<>();
        data.put("deletedRows", deleted);
        data.put("deletedId", id);
        data.put("deletedType", "Service");
        del.setInputs(data);
        del.display();
    }

    public void insert(Service record) {
        int affectedRows = serviceDAO.insert(record);
        if(affectedRows > 0) {
            Inserted<Service> insertedService = new Inserted<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", record);
            insertedService.setInputs(data);
            insertedService.display();
        }else{
            System.out.println("Insert failed");
        }
    }

    public void update(int id, Service consultation) {
        int affectedRows = serviceDAO.update(id, consultation);
        if(affectedRows > 0) {
            Updated<Service> updatedService = new Updated<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", consultation);
            data.put("updatedRows", affectedRows);
            data.put("updatedId", id);
            updatedService.setInputs(data);
            updatedService.display();
        }else{
            System.out.println("Insert failed");
        }
    }

    public Service getData(){/*
        ServiceRequestData requestServiceData = new ServiceRequestData();
        requestServiceData.display();
        HashMap<String, String> pd = requestServiceData.getInputs();

        Service consultation = new Service()
                .setPatientId(Integer.parseInt(pd.get("patientId")))
                .setServiceId(Integer.parseInt(pd.get("consultationId")))
                .setAdmissionDate(Date.valueOf(pd.get("admissionDate")))
                .setDischargeDate(Date.valueOf(pd.get("dischargeDate")))
                .setRoomNumber(Integer.parseInt(pd.get("roomNumber")))
                .setBedNumber(Integer.parseInt(pd.get("bedNumber")));
        return consultation;*/
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void index(){
        List<Service> patients = serviceDAO.selectAll();
        HashMap<String, Object> data = new HashMap<>();
        data.put("element", patients);
        Index<Service> index = new Index<>();
        index.setInputs(data);

        index.display();

        /*System.out.println("Do you want to export it to XML?");
        Scanner sc = new Scanner(System.in);
        int answer = sc.nextInt();
        if(answer == 1) {
            Services patientList = new Services();
            patientList.setServices(patients);
            exportXML("ServiceList.xml", patientList);
        }else{
            System.out.println("Going back...");
        }*/
    }

}
