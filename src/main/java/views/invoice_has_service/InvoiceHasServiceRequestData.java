package views.invoice_has_service;

import com.solvd.laba.computer_repair_service.input.single_input.StringInput;
import com.solvd.laba.computer_repair_service.views.FeedbackView;

import java.util.HashMap;

public class InvoiceHasServiceRequestData extends FeedbackView {
    public InvoiceHasServiceRequestData(HashMap<String, String> inputs){
        super(inputs);
    }

    public InvoiceHasServiceRequestData(){
        super(new HashMap<>());
    }

    public void display(){
        System.out.println("Please introduce data to associate an invoice with a service.");
    }

    public HashMap<String, String> getInputs(){
        StringInput[] stringInputs = {
                new StringInput("invoiceId",   "Total", StringInput.TypeOfString.NAME),
                new StringInput("serviceId", "Is paid (yes = 1 or higher, no = 0 or lower)", StringInput.TypeOfString.NUMBER),
                new StringInput("quantity", "Quantity of items", StringInput.TypeOfString.NUMBER),
                new StringInput("lineCost", "Line cost", StringInput.TypeOfString.DECIMAL),
        };

        processInputs(stringInputs);

        return inputs;
    }
}
