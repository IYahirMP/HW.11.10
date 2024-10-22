package menu;

import dao.factories.DAOFactory;
import models.Invoice;
import views.invoice.InvoiceOperations;

import java.util.HashMap;

public class InvoiceMenuHandler extends MenuHandler {
    public InvoiceMenuHandler(DAOFactory factory) {
        super(factory);
    }

    @Override
    public void processMenuOption() {
        InvoiceOperations ops = new InvoiceOperations();
        ops.display();
        HashMap<String,String> menuOptions = ops.getInputs();
        int val = Integer.parseInt(menuOptions.get("menuOption"));

        switch (val) {
            case 1:
                int id = invoiceController.requestId();
                invoiceController.show(id);
                break;
            case 2:
                Invoice record = invoiceController.getData();
                invoiceController.insert(record);
                break;
            case 3:
                invoiceController.index();
                break;
            case 4:
                id = invoiceController.requestId();
                invoiceController.delete(id);
                break;
            case 5:
                id = invoiceController.requestId();
                Invoice newRecord = invoiceController.getData();
                invoiceController.update(id, newRecord);
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

