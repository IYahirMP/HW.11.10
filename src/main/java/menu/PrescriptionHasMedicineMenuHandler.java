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
        PrescriptionHasMedicineOperations ops = new PrescriptionHasMedicineOperations();
        ops.display();
        HashMap<String,String> menuOptions = ops.getInputs();
        int val = Integer.parseInt(menuOptions.get("menuOption"));


        int id1 = 0, id2 = 0;
        switch (val) {
            case 1:
                id1 = invoiceHasServiceController.requestId();
                id2 = invoiceHasServiceController.requestId();
                prescriptionHasMedicineController.show(id1, id2);
                break;
            case 2:
                PrescriptionHasMedicine record = prescriptionHasMedicineController.getData();
                prescriptionHasMedicineController.insert(record);
                break;
            case 3:
                prescriptionHasMedicineController.index();
                break;
            case 4:
                id1 = invoiceHasServiceController.requestId();
                id2 = invoiceHasServiceController.requestId();
                prescriptionHasMedicineController.delete(id1, id2);
                break;
            case 5:
                PrescriptionHasMedicine newRecord = prescriptionHasMedicineController.getData();
                prescriptionHasMedicineController.update(newRecord);
                break;
            case 6:
                displayEntitySelection(currentDataSourceFactory); // Navigate back to entity selection
                break;
            default:
                displayEntitySelection(currentDataSourceFactory);
                break;
        }

        processMenuOption();
    }
}

