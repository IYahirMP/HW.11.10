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
        AdmissionRecordOperations ops = new AdmissionRecordOperations();
        ops.display();
        HashMap<String,String> menuOptions = ops.getInputs();
        int val = Integer.parseInt(menuOptions.get("menuOption"));

        switch (val) {
            case 1:
                int id = admissionRecordController.requestId();
                admissionRecordController.show(id);
                break;
            case 2:
                AdmissionRecord record = admissionRecordController.getData();
                admissionRecordController.insert(record);
                break;
            case 3:
                admissionRecordController.index();
                break;
            case 4:
                id = admissionRecordController.requestId();
                admissionRecordController.delete(id);
                break;
            case 5:
                id = admissionRecordController.requestId();
                AdmissionRecord newRecord = admissionRecordController.getData();
                admissionRecordController.update(id, newRecord);
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

