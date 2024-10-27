package menu;

import dao.factories.DAOFactory;
import models.EmergencyContact;
import views.emergency_contact.EmergencyContactOperations;

import java.util.HashMap;

public class EmergencyContactMenuHandler extends MenuHandler {
    public EmergencyContactMenuHandler(DAOFactory factory) {
        super(factory);
    }

    @Override
    public void processMenuOption() {
        logger.trace("Entering processMenuOption");

        EmergencyContactOperations ops = new EmergencyContactOperations();
        ops.display();
        HashMap<String, String> menuOptions = ops.getInputs();

        try {
            int val = Integer.parseInt(menuOptions.get("menuOption"));
            logger.debug("Menu option selected: {}", val);

            switch (val) {
                case 1:
                    int id = emergencyContactController.requestId();
                    logger.info("Fetching emergencyContact with ID: {}", id);
                    emergencyContactController.show(id);
                    break;
                case 2:
                    EmergencyContact record = emergencyContactController.getData();
                    logger.info("Inserting new emergencyContact record.");
                    emergencyContactController.insert(record);
                    break;
                case 3:
                    logger.info("Listing all emergencyContacts.");
                    emergencyContactController.index();
                    break;
                case 4:
                    id = emergencyContactController.requestId();
                    logger.info("Deleting emergencyContact with ID: {}", id);
                    emergencyContactController.delete(id);
                    break;
                case 5:
                    id = emergencyContactController.requestId();
                    EmergencyContact newRecord = emergencyContactController.getData();
                    logger.info("Updating emergencyContact with ID: {}", id);
                    emergencyContactController.update(id, newRecord);
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

