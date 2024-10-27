package menu;

import dao.factories.DAOFactory;
import models.Consultation;
import views.consultation.ConsultationOperations;

import java.util.HashMap;

public class ConsultationMenuHandler extends MenuHandler {
    public ConsultationMenuHandler(DAOFactory factory) {
        super(factory);
    }

    @Override
    public void processMenuOption() {
        logger.trace("Entering processMenuOption");

        ConsultationOperations ops = new ConsultationOperations();
        ops.display();
        HashMap<String, String> menuOptions = ops.getInputs();

        try {
            int val = Integer.parseInt(menuOptions.get("menuOption"));
            logger.debug("Menu option selected: {}", val);

            switch (val) {
                case 1:
                    int id = consultationController.requestId();
                    logger.info("Fetching consultation with ID: {}", id);
                    consultationController.show(id);
                    break;
                case 2:
                    Consultation record = consultationController.getData();
                    logger.info("Inserting new consultation record.");
                    consultationController.insert(record);
                    break;
                case 3:
                    logger.info("Listing all consultations.");
                    consultationController.index();
                    break;
                case 4:
                    id = consultationController.requestId();
                    logger.info("Deleting consultation with ID: {}", id);
                    consultationController.delete(id);
                    break;
                case 5:
                    id = consultationController.requestId();
                    Consultation newRecord = consultationController.getData();
                    logger.info("Updating consultation with ID: {}", id);
                    consultationController.update(id, newRecord);
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