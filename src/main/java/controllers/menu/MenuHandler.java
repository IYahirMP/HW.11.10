package controllers.menu;

import controllers.AdmissionRecordController;
import controllers.ConsultationController;
import controllers.PatientController;
import dao.AdmissionRecordDAO;
import dao.ConsultationDAO;
import dao.PatientDAO;
import dao.factories.DAOFactory;
import views.EntitySelection;

import java.util.HashMap;

import static java.lang.System.exit;

// Abstract Menu Handler Class
public abstract class MenuHandler {
    protected DAOFactory currentDataSourceFactory;
    protected PatientController patientController;
    protected AdmissionRecordController admissionRecordController;
    protected ConsultationController consultationController;

    public MenuHandler(DAOFactory factory) {
        // Initialize DAO and Controllers here
        currentDataSourceFactory = factory;
        PatientDAO patientDAO = currentDataSourceFactory.getPatientDAO();
        AdmissionRecordDAO admissionRecordDAO = currentDataSourceFactory.getAdmissionRecordDAO();
        ConsultationDAO consultationDAO = currentDataSourceFactory.getConsultationDAO();

        patientController = new PatientController(patientDAO);
        admissionRecordController = new AdmissionRecordController(admissionRecordDAO);
        consultationController = new ConsultationController(consultationDAO);
    }

    public static void displayEntitySelection(DAOFactory daoFactory){
        EntitySelection entitySelection = new EntitySelection();
        entitySelection.display();
        HashMap<String,String> entityOption = entitySelection.getInputs();
        int value = Integer.parseInt(entityOption.get("menuOption"));
        switch (value){
            case 1 -> new PatientMenuHandler(daoFactory).processMenuOption();
            case 2 -> new AdmissionRecordMenuHandler(daoFactory).processMenuOption();
            case 3 -> new ConsultationMenuHandler(daoFactory).processMenuOption();
            case 4,5,6,7,8,9,10 -> {
                System.out.println("Not implemented yet.");
                displayEntitySelection(daoFactory);
            }
            //case 11 -> exportDatabaseToXML();
            default -> exit(0);
        }
    }

    public abstract void processMenuOption();

}