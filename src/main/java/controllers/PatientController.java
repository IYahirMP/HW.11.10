package controllers;

import dao.PatientDAO;
import models.Patient;
import views.patient.InsertedPatient;
import views.patient.RequestPatientData;
import views.patient.ShowPatient;

import java.util.HashMap;
import java.util.Optional;

public class PatientController {
    private PatientDAO patientDAO;

    //Views
    private ShowPatient showPatient;
    private RequestPatientData requestPatientData;
    private InsertedPatient insertedPatient;

    public PatientController(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    public void show(int id) {
        Optional<Patient> patient = patientDAO.select(id);
        if(patient.isPresent()) {
            showPatient = new ShowPatient();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", patient.get());
            showPatient.setInputs(data);
            showPatient.display();
        }
    }

    public void insert() {
        requestPatientData = new RequestPatientData();
        requestPatientData.display();
        HashMap<String, String> pd = requestPatientData.getInputs();

        Patient patient = new Patient()
                .setAddress(pd.get("address"))
                .setPhone(pd.get("phone"))
                .setName(pd.get("name"))
                .setAge(Integer.parseInt(pd.get("age")));

        int affectedRows = patientDAO.insert(patient);
        if(affectedRows > 0) {
            insertedPatient = new InsertedPatient();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", patient);
            insertedPatient.setInputs(data);
            insertedPatient.display();
        }else{
            System.out.println("Insert failed");
        }
    }
}
