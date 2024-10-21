package controllers;

import dao.AdmissionRecordDAO;
import dao.PatientDAO;
import dao.factories.DAOFactory;
import models.AdmissionRecord;
import models.Patient;
import views.DataSourceSelection;
import views.EntitySelection;
import views.admission_record.AdmissionRecordOperations;
import views.patient.PatientOperations;

import java.util.HashMap;

import static java.lang.System.exit;

public class MainController {
    //Factories
    private DAOFactory currentDataSourceFactory;

    //Controllers
    private PatientController patientController;
    private AdmissionRecordController admissionRecordController;

    //Home views
    private DataSourceSelection menuView = new DataSourceSelection();
    private EntitySelection entitySelection = new EntitySelection();

    public MainController() {}

    public void start(){
        //Display main menu for selecting data source
        boolean dbSelected = displayDatabaseSelection();

        if (!dbSelected) {
            start();
        }

        displayEntitySelection();

    }

    public boolean displayDatabaseSelection(){
        menuView.display();
        HashMap<String,String> dataSourceOption = menuView.getInputs();
        int val = Integer.parseInt(dataSourceOption.get("menuOption"));

        switch(val){
            case 1,2 -> currentDataSourceFactory = DataSourceController.getDataSourceFactory(val);
            /*case 2 -> {
                System.out.println("Currently not supported.");
                return false;
            }*/
            case 3 -> exit(0);
        }

        System.out.println("Current database has been set to option " + val + ".");
        return true;
    }

    public void displayEntitySelection(){
        entitySelection.display();
        HashMap<String,String> entityOption = entitySelection.getInputs();
        int value = Integer.parseInt(entityOption.get("menuOption"));
        switch (value){
            case 1 -> showPatientMenu();
            case 2 -> showAdmissionRecordMenu();
            case 3,4,5,6,7,8,9,10 -> {
                System.out.println("Not implemented yet.");
                displayEntitySelection();
            }
            case 11 -> exportDatabaseToXML();
            default -> exit(0);
        }
    }

    public void showPatientMenu(){
        PatientOperations patientOps = new PatientOperations();
        patientOps.display();
        HashMap<String,String> menuOptions = patientOps.getInputs();
        int val = Integer.parseInt(menuOptions.get("menuOption"));

        PatientDAO patientDAO = currentDataSourceFactory.getPatientDAO();
        patientController = new PatientController(patientDAO);

        switch(val){
            case 1 -> {
                int id = patientController.requestPatientId();
                patientController.show(id);
            }
            case 2 -> {
                Patient patient = patientController.getPatientData();
                patientController.insert(patient);
            }
            case 3 -> patientController.index();
            case 4 -> {
                int id = patientController.requestPatientId();
                patientController.delete(id);
            }
            case 5 -> {
                int id = patientController.requestPatientId();
                Patient patient = patientController.getPatientData();
                patientController.update(id, patient);
            }
            case 6 -> displayEntitySelection();
            default -> displayEntitySelection();
        }

        showPatientMenu();
    }

    public void showAdmissionRecordMenu(){
        AdmissionRecordOperations ops = new AdmissionRecordOperations();
        ops.display();
        HashMap<String,String> menuOptions = ops.getInputs();
        int val = Integer.parseInt(menuOptions.get("menuOption"));

        AdmissionRecordDAO admissionRecordDAO = currentDataSourceFactory.getAdmissionRecordDAO();
        admissionRecordController = new AdmissionRecordController(admissionRecordDAO);

        switch(val){
            case 1 -> {
                int id = admissionRecordController.requestId();
                admissionRecordController.show(id);
            }
            case 2 -> {
                AdmissionRecord record = admissionRecordController.getData();
                admissionRecordController.insert(record);
            }
            case 3 -> admissionRecordController.index();
            case 4 -> {
                int id = admissionRecordController.requestId();
                admissionRecordController.delete(id);
            }
            case 5 -> {
                int id = admissionRecordController.requestId();
                AdmissionRecord record = admissionRecordController.getData();
                admissionRecordController.update(id, record);
            }
            case 6 -> displayEntitySelection();
            default -> displayEntitySelection();
        }

        showAdmissionRecordMenu();
    }

    public void exportDatabaseToXML(){
        return;
    }
}
