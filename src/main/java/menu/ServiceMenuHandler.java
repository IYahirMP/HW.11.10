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
        ServiceOperations ops = new ServiceOperations();
        ops.display();
        HashMap<String,String> menuOptions = ops.getInputs();
        int val = Integer.parseInt(menuOptions.get("menuOption"));

        switch (val) {
            case 1:
                int id = serviceController.requestId();
                serviceController.show(id);
                break;
            case 2:
                Service record = serviceController.getData();
                serviceController.insert(record);
                break;
            case 3:
                serviceController.index();
                break;
            case 4:
                id = serviceController.requestId();
                serviceController.delete(id);
                break;
            case 5:
                id = serviceController.requestId();
                Service newRecord = serviceController.getData();
                serviceController.update(id, newRecord);
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

