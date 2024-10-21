package controllers.menu;

import dao.factories.DAOFactory;
import models.Patient;
import views.patient.PatientOperations;

import java.util.HashMap;

public class PatientMenuHandler extends MenuHandler {
    public PatientMenuHandler(DAOFactory factory) {
        super(factory);
    }

    @Override
    public void processMenuOption() {
        PatientOperations patientOps = new PatientOperations();
        patientOps.display();
        HashMap<String,String> menuOptions = patientOps.getInputs();
        int val = Integer.parseInt(menuOptions.get("menuOption"));

        switch (val) {
            case 1:
                int id = patientController.requestPatientId();
                patientController.show(id);
                break;
            case 2:
                Patient patient = patientController.getPatientData();
                patientController.insert(patient);
                break;
            case 3:
                patientController.index();
                break;
            case 4:
                id = patientController.requestPatientId();
                patientController.delete(id);
                break;
            case 5:
                id = patientController.requestPatientId();
                Patient newPatient = patientController.getPatientData();
                patientController.update(id, newPatient);
                break;
            case 6:
                displayEntitySelection(currentDataSourceFactory); // Navigate back to entity selection
                break;
            default:
                displayEntitySelection(currentDataSourceFactory);
                break;
        }

        processMenuOption();
    }
}