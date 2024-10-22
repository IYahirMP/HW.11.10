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
        TreatmentRecordOperations ops = new TreatmentRecordOperations();
        ops.display();
        HashMap<String,String> menuOptions = ops.getInputs();
        int val = Integer.parseInt(menuOptions.get("menuOption"));

        switch (val) {
            case 1:
                int id = treatmentRecordController.requestId();
                treatmentRecordController.show(id);
                break;
            case 2:
                TreatmentRecord record = treatmentRecordController.getData();
                treatmentRecordController.insert(record);
                break;
            case 3:
                treatmentRecordController.index();
                break;
            case 4:
                id = treatmentRecordController.requestId();
                treatmentRecordController.delete(id);
                break;
            case 5:
                id = treatmentRecordController.requestId();
                TreatmentRecord newRecord = treatmentRecordController.getData();
                treatmentRecordController.update(id, newRecord);
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

