package controllers;

import dao.ConsultationDAO;

import models.Consultation;
import views.generic.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class ConsultationController {
    private final ConsultationDAO consultationDAO;

    public ConsultationController(ConsultationDAO consultationDAO) {
        this.consultationDAO = consultationDAO;
    }

    public int requestId(){
        RequestId req = new RequestId("Consultation");
        HashMap<String, String> patientOptions = req.getInputs();
        int id = Integer.parseInt(patientOptions.get("id"));
        return id;
    }

    public void show(int id) {
        Optional<Consultation> record = consultationDAO.select(id);
        if(record.isPresent()) {
            //Views
            Show<Consultation> showConsultation = new Show<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", record.get());
            showConsultation.setInputs(data);
            showConsultation.display();
        }
        else{
            System.out.println("Consultation not found");
        }
    }

    public void delete(int id) {
        int deleted = consultationDAO.delete(id);
        Deleted del = new Deleted();
        HashMap<String, Object> data = new HashMap<>();
        data.put("deletedRows", deleted);
        data.put("deletedId", id);
        data.put("deletedType", "Consultation");
        del.setInputs(data);
        del.display();
    }

    public void insert(Consultation record) {
        int affectedRows = consultationDAO.insert(record);
        if(affectedRows > 0) {
            Inserted<Consultation> insertedConsultation = new Inserted<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", record);
            insertedConsultation.setInputs(data);
            insertedConsultation.display();
        }else{
            System.out.println("Insert failed");
        }
    }

    public void update(int id, Consultation consultation) {
        int affectedRows = consultationDAO.update(id, consultation);
        if(affectedRows > 0) {
            Updated<Consultation> updatedConsultation = new Updated<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", consultation);
            data.put("updatedRows", affectedRows);
            data.put("updatedId", id);
            updatedConsultation.setInputs(data);
            updatedConsultation.display();
        }else{
            System.out.println("Insert failed");
        }
    }

    public Consultation getData(){/*
        ConsultationRequestData requestConsultationData = new ConsultationRequestData();
        requestConsultationData.display();
        HashMap<String, String> pd = requestConsultationData.getInputs();

        Consultation consultation = new Consultation()
                .setPatientId(Integer.parseInt(pd.get("patientId")))
                .setConsultationId(Integer.parseInt(pd.get("consultationId")))
                .setAdmissionDate(Date.valueOf(pd.get("admissionDate")))
                .setDischargeDate(Date.valueOf(pd.get("dischargeDate")))
                .setRoomNumber(Integer.parseInt(pd.get("roomNumber")))
                .setBedNumber(Integer.parseInt(pd.get("bedNumber")));
        return consultation;*/
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void index(){
        List<Consultation> patients = consultationDAO.selectAll();
        HashMap<String, Object> data = new HashMap<>();
        data.put("element", patients);
        Index<Consultation> index = new Index<>();
        index.setInputs(data);

        index.display();

        /*System.out.println("Do you want to export it to XML?");
        Scanner sc = new Scanner(System.in);
        int answer = sc.nextInt();
        if(answer == 1) {
            Consultations patientList = new Consultations();
            patientList.setConsultations(patients);
            exportXML("ConsultationList.xml", patientList);
        }else{
            System.out.println("Going back...");
        }*/
    }

}
