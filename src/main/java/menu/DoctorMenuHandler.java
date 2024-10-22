package menu;

import dao.factories.DAOFactory;
import models.Doctor;
import views.doctor.DoctorOperations;

import java.util.HashMap;

public class DoctorMenuHandler extends MenuHandler {
    public DoctorMenuHandler(DAOFactory factory) {
        super(factory);
    }

    @Override
    public void processMenuOption() {
        DoctorOperations ops = new DoctorOperations();
        ops.display();
        HashMap<String,String> menuOptions = ops.getInputs();
        int val = Integer.parseInt(menuOptions.get("menuOption"));

        switch (val) {
            case 1:
                int id = doctorController.requestId();
                doctorController.show(id);
                break;
            case 2:
                Doctor record = doctorController.getData();
                doctorController.insert(record);
                break;
            case 3:
                doctorController.index();
                break;
            case 4:
                id = doctorController.requestId();
                doctorController.delete(id);
                break;
            case 5:
                id = doctorController.requestId();
                Doctor newRecord = doctorController.getData();
                doctorController.update(id, newRecord);
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

