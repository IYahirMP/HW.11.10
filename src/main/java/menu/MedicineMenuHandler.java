package menu;

import dao.factories.DAOFactory;
import models.Medicine;
import models.Medicine;
import views.medicine.MedicineOperations;
import views.medicine.MedicineOperations;

import java.util.HashMap;

public class MedicineMenuHandler extends MenuHandler {
    public MedicineMenuHandler(DAOFactory factory) {
        super(factory);
    }

    @Override
    public void processMenuOption() {
        logger.trace("Entering processMenuOption");

        MedicineOperations ops = new MedicineOperations();
        ops.display();
        HashMap<String, String> menuOptions = ops.getInputs();

        try {
            int val = Integer.parseInt(menuOptions.get("menuOption"));
            logger.debug("Menu option selected: {}", val);

            switch (val) {
                case 1:
                    int id = medicineController.requestId();
                    logger.info("Fetching medicine with ID: {}", id);
                    medicineController.show(id);
                    break;
                case 2:
                    Medicine record = medicineController.getData();
                    logger.info("Inserting new medicine record.");
                    medicineController.insert(record);
                    break;
                case 3:
                    logger.info("Listing all medicines.");
                    medicineController.index();
                    break;
                case 4:
                    id = medicineController.requestId();
                    logger.info("Deleting medicine with ID: {}", id);
                    medicineController.delete(id);
                    break;
                case 5:
                    id = medicineController.requestId();
                    Medicine newRecord = medicineController.getData();
                    logger.info("Updating medicine with ID: {}", id);
                    medicineController.update(id, newRecord);
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

