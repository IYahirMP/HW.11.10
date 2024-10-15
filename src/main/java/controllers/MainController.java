package controllers;

import dao.PatientDAO;
import dao.factories.DAOFactory;
import models.Patient;
import views.DataSourceSelection;
import views.EntitySelection;
import views.patient.Operations;

import java.util.HashMap;

import static java.lang.System.exit;

public class MainController {
    //Factories
    private DAOFactory currentDataSourceFactory;

    //Controllers
    private PatientController patientController;

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
            case 1 -> currentDataSourceFactory = DataSourceController.getDataSourceFactory(val);
            case 2 -> {
                System.out.println("Currently not supported.");
                return false;
            }
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
            case 2,3,4,5,6,7,8,9,10 -> {
                System.out.println("Not implemented yet.");
                displayEntitySelection();
            }
            default -> exit(0);
        }
    }

    public void showPatientMenu(){
        Operations patientOps = new Operations();
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
            default -> displayDatabaseSelection();
        }

        showPatientMenu();
    }
}
