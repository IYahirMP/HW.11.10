package controllers;

import controllers.menu.AdmissionRecordMenuHandler;
import controllers.menu.PatientMenuHandler;
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

import static controllers.menu.MenuHandler.displayEntitySelection;
import static java.lang.System.exit;

public class MainController {
    //Factories
    private DAOFactory currentDataSourceFactory;

    //Controllers
    private PatientController patientController;
    private AdmissionRecordController admissionRecordController;

    //Home views
    private DataSourceSelection menuView = new DataSourceSelection();
    public MainController() {}

    public void start(){
        //Display main menu for selecting data source
        boolean dbSelected = displayDatabaseSelection();

        if (!dbSelected) {
            start();
        }

        displayEntitySelection(currentDataSourceFactory);

    }

    public boolean displayDatabaseSelection(){
        menuView.display();
        HashMap<String,String> dataSourceOption = menuView.getInputs();
        int val = Integer.parseInt(dataSourceOption.get("menuOption"));

        switch(val){
            case 1,2 -> currentDataSourceFactory = DataSourceController.getDataSourceFactory(val);
            case 3 -> exit(0);
        }

        System.out.println("Current database has been set to option " + val + ".");
        return true;
    }

}
