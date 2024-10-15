package controllers;

import dao.PatientDAO;
import models.Patient;
import views.patient.ShowPatient;

import java.util.HashMap;
import java.util.Optional;

public class PatientController {
    private PatientDAO patientDAO;

    //Views
    private ShowPatient showPatient;

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
}
