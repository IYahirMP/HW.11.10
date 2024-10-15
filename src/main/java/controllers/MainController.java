package controllers;

import dao.PatientDAO;
import dao.factories.DAOFactory;
import dao.factories.MySQLDAOFactory;
import views.DataSourceSelection;
import views.EntitySelection;
import views.patient.PatientOps;
import views.patient.RequestPatientId;

import java.util.HashMap;

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
        displayDatabaseSelection();

        entitySelection.display();
        HashMap<String,String> entityOption = entitySelection.getInputs();
        int value = Integer.parseInt(entityOption.get("menuOption"));
        switch (value){
            case 1: showPatientMenu();
        }
    }

    public void displayDatabaseSelection(){
        menuView.display();
        HashMap<String,String> dataSourceOption = menuView.getInputs();
        int val = Integer.parseInt(dataSourceOption.get("menuOption"));
        currentDataSourceFactory = DataSourceController.getDataSourceFactory(val);
        System.out.println("Current database has been set to option " + val + ".");
    }

    public void showPatientMenu(){
        PatientOps patientOps = new PatientOps();
        patientOps.display();
        HashMap<String,String> menuOptions = patientOps.getInputs();
        int val = Integer.parseInt(menuOptions.get("menuOption"));

        PatientDAO patientDAO = currentDataSourceFactory.getPatientDAO();
        patientController = new PatientController(patientDAO);

        switch(val){
            case 1 -> {
                RequestPatientId req = new RequestPatientId();
                HashMap<String, String> patientOptions = req.getInputs();
                int id = Integer.parseInt(patientOptions.get("id"));
                patientController.show(id);
            }
            case 2 -> patientController.insert();
            case 3 -> patientController.index();

        }
    }
}
