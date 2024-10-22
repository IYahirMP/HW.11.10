package menu;

import dao.factories.DAOFactory;
import models.Consultation;
import views.consultation.ConsultationOperations;

import java.util.HashMap;

public class ConsultationMenuHandler extends MenuHandler {
    public ConsultationMenuHandler(DAOFactory factory) {
        super(factory);
    }

    @Override
    public void processMenuOption() {
        ConsultationOperations consultationOps = new ConsultationOperations();
        consultationOps.display();
        HashMap<String,String> menuOptions = consultationOps.getInputs();
        int val = Integer.parseInt(menuOptions.get("menuOption"));

        switch (val) {
            case 1:
                int id = consultationController.requestId();
                consultationController.show(id);
                break;
            case 2:
                Consultation patient = consultationController.getData();
                consultationController.insert(patient);
                break;
            case 3:
                consultationController.index();
                break;
            case 4:
                id = consultationController.requestId();
                consultationController.delete(id);
                break;
            case 5:
                id = consultationController.requestId();
                Consultation newPatient = consultationController.getData();
                consultationController.update(id, newPatient);
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