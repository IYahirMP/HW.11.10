package menu;

import dao.factories.DAOFactory;
import models.Service;
import views.service.ServiceOperations;

import java.util.HashMap;

public class ServiceMenuHandler extends MenuHandler {
    public ServiceMenuHandler(DAOFactory factory) {
        super(factory);
    }

    @Override
    public void processMenuOption() {
        logger.trace("Entering processMenuOption");

        ServiceOperations ops = new ServiceOperations();
        ops.display();
        HashMap<String, String> menuOptions = ops.getInputs();

        try {
            int val = Integer.parseInt(menuOptions.get("menuOption"));
            logger.debug("Menu option selected: {}", val);

            switch (val) {
                case 1:
                    int id = serviceController.requestId();
                    logger.info("Fetching service with ID: {}", id);
                    serviceController.show(id);
                    break;
                case 2:
                    Service record = serviceController.getData();
                    logger.info("Inserting new service record.");
                    serviceController.insert(record);
                    break;
                case 3:
                    logger.info("Listing all services.");
                    serviceController.index();
                    break;
                case 4:
                    id = serviceController.requestId();
                    logger.info("Deleting service with ID: {}", id);
                    serviceController.delete(id);
                    break;
                case 5:
                    id = serviceController.requestId();
                    Service newRecord = serviceController.getData();
                    logger.info("Updating service with ID: {}", id);
                    serviceController.update(id, newRecord);
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

