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
        logger.trace("Entering processMenuOption");

        InvoiceHasServiceOperations ops = new InvoiceHasServiceOperations();
        ops.display();
        HashMap<String, String> menuOptions = ops.getInputs();

        try {
            int val = Integer.parseInt(menuOptions.get("menuOption"));
            logger.debug("Menu option selected: {}", val);

            int invoiceId, serviceId;

            switch (val) {
                case 1:
                    invoiceId = invoiceHasServiceController.requestId();
                    serviceId = invoiceHasServiceController.requestId();
                    logger.info("Fetching invoiceHasService invoiceId: {} and serviceId: {}", invoiceId, serviceId);
                    invoiceHasServiceController.show(invoiceId, serviceId);
                    break;
                case 2:
                    InvoiceHasService record = invoiceHasServiceController.getData();
                    logger.info("Inserting new invoiceHasService record.");
                    invoiceHasServiceController.insert(record);
                    break;
                case 3:
                    logger.info("Listing all invoiceHasServices.");
                    invoiceHasServiceController.index();
                    break;
                case 4:
                    invoiceId = invoiceHasServiceController.requestId();
                    serviceId = invoiceHasServiceController.requestId();
                    logger.info("Deleting invoiceHasService with invoiceId: {} and serviceId: {}", invoiceId, serviceId);
                    break;
                case 5:
                    InvoiceHasService newRecord = invoiceHasServiceController.getData();
                    logger.info("Updating invoiceHasService with ID: ({},{})", newRecord.getInvoiceId(), newRecord.getServiceId());
                    invoiceHasServiceController.update(newRecord);
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

