package menu;

import dao.factories.DAOFactory;
import models.InvoiceHasService;
import views.invoice_has_service.InvoiceHasServiceOperations;

import java.util.HashMap;

public class InvoiceHasServiceMenuHandler extends MenuHandler {
    public InvoiceHasServiceMenuHandler(DAOFactory factory) {
        super(factory);
    }

    @Override
    public void processMenuOption() {
        InvoiceHasServiceOperations ops = new InvoiceHasServiceOperations();
        ops.display();
        HashMap<String,String> menuOptions = ops.getInputs();
        int val = Integer.parseInt(menuOptions.get("menuOption"));

        switch (val) {
            case 1:
                int id = invoiceHasServiceController.requestId();
                invoiceHasServiceController.show(id);
                break;
            case 2:
                InvoiceHasService record = invoiceHasServiceController.getData();
                invoiceHasServiceController.insert(record);
                break;
            case 3:
                invoiceHasServiceController.index();
                break;
            case 4:
                id = invoiceHasServiceController.requestId();
                invoiceHasServiceController.delete(id);
                break;
            case 5:
                id = invoiceHasServiceController.requestId();
                InvoiceHasService newRecord = invoiceHasServiceController.getData();
                invoiceHasServiceController.update(id, newRecord);
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

