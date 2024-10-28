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
        logger.trace("Entering processMenuOption");

        InvoiceOperations ops = new InvoiceOperations();
        ops.display();
        HashMap<String, String> menuOptions = ops.getInputs();

        try {
            int val = Integer.parseInt(menuOptions.get("menuOption"));
            logger.debug("Menu option selected: {}", val);

            switch (val) {
                case 1:
                    int id = invoiceController.requestId();
                    logger.info("Fetching invoice with ID: {}", id);
                    invoiceController.show(id);
                    break;
                case 2:
                    Invoice record = invoiceController.getData();
                    logger.info("Inserting new invoice record.");
                    invoiceController.insert(record);
                    break;
                case 3:
                    logger.info("Listing all invoices.");
                    invoiceController.index();
                    break;
                case 4:
                    id = invoiceController.requestId();
                    logger.info("Deleting invoice with ID: {}", id);
                    invoiceController.delete(id);
                    break;
                case 5:
                    id = invoiceController.requestId();
                    Invoice newRecord = invoiceController.getData();
                    logger.info("Updating invoice with ID: {}", id);
                    invoiceController.update(id, newRecord);
                    break;
                case 6:
                    logger.info("Navigating back to entity selection.");
                    displayEntitySelection(currentDataSourceFactory);
                    break;
                default:
                    logger.warn("Invalid menu option selected: {}", val);
                    displayEntitySelection(currentDataSourceFactory);
                    break;
            }

        } catch (NumberFormatException e) {
            logger.error("Invalid input format for menu option selection: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("An unexpected error occurred: {}", e.getMessage(), e);
        }

        logger.trace("Exiting processMenuOption");
        processMenuOption();
    }
}

