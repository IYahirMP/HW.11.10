package controllers;

import dao.PatientDAO;
import models.Patient;
import views.patient.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class PatientController {
    private PatientDAO patientDAO;

    //Views
    private Show showPatient;
    private RequestData requestPatientData;
    private Inserted insertedPatient;

    public PatientController(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    public int requestPatientId(){
        RequestId req = new RequestId();
        HashMap<String, String> patientOptions = req.getInputs();
        int id = Integer.parseInt(patientOptions.get("id"));
        return id;
    }

    public void show(int id) {
        Optional<Patient> patient = patientDAO.select(id);
        if(patient.isPresent()) {
            showPatient = new Show();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", patient.get());
            showPatient.setInputs(data);
            showPatient.display();
        }
    }

    public void delete(int id) {
        int deleted = patientDAO.delete(id);
        Deleted del = new Deleted();
        HashMap<String, Object> data = new HashMap<>();
        data.put("deletedRows", deleted);
        data.put("deletedId", id);
        del.setInputs(data);
        del.display();
    }

    public void insert() {
        requestPatientData = new RequestData();
        requestPatientData.display();
        HashMap<String, String> pd = requestPatientData.getInputs();

        Patient patient = new Patient()
                .setAddress(pd.get("address"))
                .setPhone(pd.get("phone"))
                .setName(pd.get("name"))
                .setAge(Integer.parseInt(pd.get("age")));

        int affectedRows = patientDAO.insert(patient);
        if(affectedRows > 0) {
            insertedPatient = new Inserted();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", patient);
            insertedPatient.setInputs(data);
            insertedPatient.display();
        }else{
            System.out.println("Insert failed");
        }
    }

    public void index(){
        List<Patient> patients = patientDAO.selectAll();
        HashMap<String, Object> data = new HashMap<>();
        data.put("element", patients);
        Index index = new Index();
        index.setInputs(data);

        index.display();
    }
}
