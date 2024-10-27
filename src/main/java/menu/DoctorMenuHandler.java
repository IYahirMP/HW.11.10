package menu;

import dao.factories.DAOFactory;
import models.Doctor;
import views.doctor.DoctorOperations;

import java.util.HashMap;

public class DoctorMenuHandler extends MenuHandler {
    public DoctorMenuHandler(DAOFactory factory) {
        super(factory);
    }

    @Override
    public void processMenuOption() {
        logger.trace("Entering processMenuOption");

        DoctorOperations ops = new DoctorOperations();
        ops.display();
        HashMap<String, String> menuOptions = ops.getInputs();

        try {
            int val = Integer.parseInt(menuOptions.get("menuOption"));
            logger.debug("Menu option selected: {}", val);

            switch (val) {
                case 1:
                    int id = doctorController.requestId();
                    logger.info("Fetching doctor with ID: {}", id);
                    doctorController.show(id);
                    break;
                case 2:
                    Doctor record = doctorController.getData();
                    logger.info("Inserting new doctor record.");
                    doctorController.insert(record);
                    break;
                case 3:
                    logger.info("Listing all doctors.");
                    doctorController.index();
                    break;
                case 4:
                    id = doctorController.requestId();
                    logger.info("Deleting doctor with ID: {}", id);
                    doctorController.delete(id);
                    break;
                case 5:
                    id = doctorController.requestId();
                    Doctor newRecord = doctorController.getData();
                    logger.info("Updating doctor with ID: {}", id);
                    doctorController.update(id, newRecord);
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

