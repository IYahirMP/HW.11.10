package menu;

import dao.factories.DAOFactory;
import models.TreatmentRecord;
import views.treatment_record.TreatmentRecordOperations;

import java.util.HashMap;

public class TreatmentRecordMenuHandler extends MenuHandler {
    public TreatmentRecordMenuHandler(DAOFactory factory) {
        super(factory);
    }

    @Override
    public void processMenuOption() {
        logger.trace("Entering processMenuOption");

        TreatmentRecordOperations ops = new TreatmentRecordOperations();
        ops.display();
        HashMap<String, String> menuOptions = ops.getInputs();

        try {
            int val = Integer.parseInt(menuOptions.get("menuOption"));
            logger.debug("Menu option selected: {}", val);

            switch (val) {
                case 1:
                    int id = treatmentRecordController.requestId();
                    logger.info("Fetching treatmentRecord with ID: {}", id);
                    treatmentRecordController.show(id);
                    break;
                case 2:
                    TreatmentRecord record = treatmentRecordController.getData();
                    logger.info("Inserting new treatmentRecord record.");
                    treatmentRecordController.insert(record);
                    break;
                case 3:
                    logger.info("Listing all treatmentRecords.");
                    treatmentRecordController.index();
                    break;
                case 4:
                    id = treatmentRecordController.requestId();
                    logger.info("Deleting treatmentRecord with ID: {}", id);
                    treatmentRecordController.delete(id);
                    break;
                case 5:
                    id = treatmentRecordController.requestId();
                    TreatmentRecord newRecord = treatmentRecordController.getData();
                    logger.info("Updating treatmentRecord with ID: {}", id);
                    treatmentRecordController.update(id, newRecord);
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

