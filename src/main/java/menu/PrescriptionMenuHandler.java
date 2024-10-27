package menu;

import dao.factories.DAOFactory;
import models.Prescription;
import models.Prescription;
import views.prescription.PrescriptionOperations;
import views.prescription.PrescriptionOperations;

import java.util.HashMap;

public class PrescriptionMenuHandler extends MenuHandler {
    public PrescriptionMenuHandler(DAOFactory factory) {
        super(factory);
    }

    @Override
    public void processMenuOption() {
        logger.trace("Entering processMenuOption");

        PrescriptionOperations ops = new PrescriptionOperations();
        ops.display();
        HashMap<String, String> menuOptions = ops.getInputs();

        try {
            int val = Integer.parseInt(menuOptions.get("menuOption"));
            logger.debug("Menu option selected: {}", val);

            switch (val) {
                case 1:
                    int id = prescriptionController.requestId();
                    logger.info("Fetching prescription with ID: {}", id);
                    prescriptionController.show(id);
                    break;
                case 2:
                    Prescription record = prescriptionController.getData();
                    logger.info("Inserting new prescription record.");
                    prescriptionController.insert(record);
                    break;
                case 3:
                    logger.info("Listing all prescriptions.");
                    prescriptionController.index();
                    break;
                case 4:
                    id = prescriptionController.requestId();
                    logger.info("Deleting prescription with ID: {}", id);
                    prescriptionController.delete(id);
                    break;
                case 5:
                    id = prescriptionController.requestId();
                    Prescription newRecord = prescriptionController.getData();
                    logger.info("Updating prescription with ID: {}", id);
                    prescriptionController.update(id, newRecord);
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

