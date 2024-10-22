package menu;

import dao.factories.DAOFactory;
import models.EmergencyContact;
import views.emergency_contact.EmergencyContactOperations;

import java.util.HashMap;

public class EmergencyContactMenuHandler extends MenuHandler {
    public EmergencyContactMenuHandler(DAOFactory factory) {
        super(factory);
    }

    @Override
    public void processMenuOption() {
        EmergencyContactOperations ops = new EmergencyContactOperations();
        ops.display();
        HashMap<String,String> menuOptions = ops.getInputs();
        int val = Integer.parseInt(menuOptions.get("menuOption"));

        switch (val) {
            case 1:
                int id = emergencyContactController.requestId();
                emergencyContactController.show(id);
                break;
            case 2:
                EmergencyContact record = emergencyContactController.getData();
                emergencyContactController.insert(record);
                break;
            case 3:
                emergencyContactController.index();
                break;
            case 4:
                id = emergencyContactController.requestId();
                emergencyContactController.delete(id);
                break;
            case 5:
                id = emergencyContactController.requestId();
                EmergencyContact newRecord = emergencyContactController.getData();
                emergencyContactController.update(id, newRecord);
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

