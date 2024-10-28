package menu;

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
        logger.trace("Entering processMenuOption");

        PatientOperations ops = new PatientOperations();
        ops.display();
        HashMap<String, String> menuOptions = ops.getInputs();

        try {
            int val = Integer.parseInt(menuOptions.get("menuOption"));
            logger.debug("Menu option selected: {}", val);

            switch (val) {
                case 1:
                    int id = patientController.requestPatientId();
                    logger.info("Fetching patient with ID: {}", id);
                    patientController.show(id);
                    break;
                case 2:
                    Patient record = patientController.getPatientData();
                    logger.info("Inserting new patient record.");
                    patientController.insert(record);
                    break;
                case 3:
                    logger.info("Listing all patients.");
                    patientController.index();
                    break;
                case 4:
                    id = patientController.requestPatientId();
                    logger.info("Deleting patient with ID: {}", id);
                    patientController.delete(id);
                    break;
                case 5:
                    id = patientController.requestPatientId();
                    Patient newRecord = patientController.getPatientData();
                    logger.info("Updating patient with ID: {}", id);
                    patientController.update(id, newRecord);
                    break;
                case 6:
                    logger.info("Navigating back to entity selection.");
                    displayEntitySelection(currentDataSourceFactory);
                    break;
                default:
                    logger.warn("Invalid menu option selected: {}", val);
                    displayEntitySelection(currentDataSourceFactory);
                    break;
            }

        } catch (NumberFormatException e) {
            logger.error("Invalid input format for menu option selection: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("An unexpected error occurred: {}", e.getMessage(), e);
        }

        logger.trace("Exiting processMenuOption");
        processMenuOption();
    }
}