package menu;

import dao.factories.DAOFactory;
import models.AdmissionRecord;
import views.admission_record.AdmissionRecordOperations;

import java.util.HashMap;

public class AdmissionRecordMenuHandler extends MenuHandler {
    public AdmissionRecordMenuHandler(DAOFactory factory) {
        super(factory);
    }

    @Override
    public void processMenuOption() {
        logger.trace("Entering processMenuOption");

        AdmissionRecordOperations ops = new AdmissionRecordOperations();
        ops.display();
        HashMap<String, String> menuOptions = ops.getInputs();

        try {
            int val = Integer.parseInt(menuOptions.get("menuOption"));
            logger.debug("Menu option selected: {}", val);

            switch (val) {
                case 1:
                    int id = admissionRecordController.requestId();
                    logger.info("Fetching admissionRecord with ID: {}", id);
                    admissionRecordController.show(id);
                    break;
                case 2:
                    AdmissionRecord record = admissionRecordController.getData();
                    logger.info("Inserting new admissionRecord record.");
                    admissionRecordController.insert(record);
                    break;
                case 3:
                    logger.info("Listing all admissionRecords.");
                    admissionRecordController.index();
                    break;
                case 4:
                    id = admissionRecordController.requestId();
                    logger.info("Deleting admissionRecord with ID: {}", id);
                    admissionRecordController.delete(id);
                    break;
                case 5:
                    id = admissionRecordController.requestId();
                    AdmissionRecord newRecord = admissionRecordController.getData();
                    logger.info("Updating admissionRecord with ID: {}", id);
                    admissionRecordController.update(id, newRecord);
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

