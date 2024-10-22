package menu;

import dao.factories.DAOFactory;
import models.Prescription;
import views.prescription.PrescriptionOperations;

import java.util.HashMap;

public class PrescriptionMenuHandler extends MenuHandler {
    public PrescriptionMenuHandler(DAOFactory factory) {
        super(factory);
    }

    @Override
    public void processMenuOption() {
        PrescriptionOperations ops = new PrescriptionOperations();
        ops.display();
        HashMap<String,String> menuOptions = ops.getInputs();
        int val = Integer.parseInt(menuOptions.get("menuOption"));

        switch (val) {
            case 1:
                int id = prescriptionController.requestId();
                prescriptionController.show(id);
                break;
            case 2:
                Prescription record = prescriptionController.getData();
                prescriptionController.insert(record);
                break;
            case 3:
                prescriptionController.index();
                break;
            case 4:
                id = prescriptionController.requestId();
                prescriptionController.delete(id);
                break;
            case 5:
                id = prescriptionController.requestId();
                Prescription newRecord = prescriptionController.getData();
                prescriptionController.update(id, newRecord);
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

