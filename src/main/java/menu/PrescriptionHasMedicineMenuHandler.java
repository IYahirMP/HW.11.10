package menu;

import dao.factories.DAOFactory;
import models.PrescriptionHasMedicine;
import views.prescription_has_medicine.PrescriptionHasMedicineOperations;

import java.util.HashMap;

public class PrescriptionHasMedicineMenuHandler extends MenuHandler {
    public PrescriptionHasMedicineMenuHandler(DAOFactory factory) {
        super(factory);
    }

    @Override
    public void processMenuOption() {
        logger.trace("Entering processMenuOption");

        PrescriptionHasMedicineOperations ops = new PrescriptionHasMedicineOperations();
        ops.display();
        HashMap<String, String> menuOptions = ops.getInputs();

        try {
            int val = Integer.parseInt(menuOptions.get("menuOption"));
            logger.debug("Menu option selected: {}", val);

            int prescriptionId, medicineId;

            switch (val) {
                case 1:
                    prescriptionId = prescriptionHasMedicineController.requestId();
                    medicineId = prescriptionHasMedicineController.requestId();
                    logger.info("Fetching prescriptionHasMedicine prescriptionId: {} and medicineId: {}", prescriptionId, medicineId);
                    prescriptionHasMedicineController.show(prescriptionId, medicineId);
                    break;
                case 2:
                    PrescriptionHasMedicine record = prescriptionHasMedicineController.getData();
                    logger.info("Inserting new prescriptionHasMedicine record.");
                    prescriptionHasMedicineController.insert(record);
                    break;
                case 3:
                    logger.info("Listing all prescriptionHasMedicines.");
                    prescriptionHasMedicineController.index();
                    break;
                case 4:
                    prescriptionId = prescriptionHasMedicineController.requestId();
                    medicineId = prescriptionHasMedicineController.requestId();
                    logger.info("Deleting prescriptionHasMedicine with prescriptionId: {} and medicineId: {}", prescriptionId, medicineId);
                    break;
                case 5:
                    PrescriptionHasMedicine newRecord = prescriptionHasMedicineController.getData();
                    logger.info("Updating prescriptionHasMedicine with ID: ({},{})", newRecord.getPrescriptionId(), newRecord.getMedicineId());
                    prescriptionHasMedicineController.update(newRecord);
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

