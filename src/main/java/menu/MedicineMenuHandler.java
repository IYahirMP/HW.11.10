package menu;

import dao.factories.DAOFactory;
import models.Medicine;
import views.medicine.MedicineOperations;

import java.util.HashMap;

public class MedicineMenuHandler extends MenuHandler {
    public MedicineMenuHandler(DAOFactory factory) {
        super(factory);
    }

    @Override
    public void processMenuOption() {
        MedicineOperations ops = new MedicineOperations();
        ops.display();
        HashMap<String,String> menuOptions = ops.getInputs();
        int val = Integer.parseInt(menuOptions.get("menuOption"));

        switch (val) {
            case 1:
                int id = medicineController.requestId();
                medicineController.show(id);
                break;
            case 2:
                Medicine record = medicineController.getData();
                medicineController.insert(record);
                break;
            case 3:
                medicineController.index();
                break;
            case 4:
                id = medicineController.requestId();
                medicineController.delete(id);
                break;
            case 5:
                id = medicineController.requestId();
                Medicine newRecord = medicineController.getData();
                medicineController.update(id, newRecord);
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

