package views.invoice;

import com.solvd.laba.computer_repair_service.input.single_input.StringInput;
import com.solvd.laba.computer_repair_service.views.FeedbackView;

import java.util.HashMap;

public class InvoiceRequestData extends FeedbackView {
    public InvoiceRequestData(HashMap<String, String> inputs){
        super(inputs);
    }

    public InvoiceRequestData(){
        super(new HashMap<>());
    }

    public void display(){
        System.out.println("Please, introduce the invoice data.");
    }

    public HashMap<String, String> getInputs(){
        StringInput[] stringInputs = {
                new StringInput("total",   "Total", StringInput.TypeOfString.NAME),
                new StringInput("isPaid", "Is paid (yes = 1 or higher, no = 0 or lower)", StringInput.TypeOfString.NUMBER),
                new StringInput("patientId", "Associated patient id", StringInput.TypeOfString.NUMBER),
                new StringInput("paymentDate", "Payment date", StringInput.TypeOfString.DATE),
        };

        processInputs(stringInputs);

        return inputs;
    }
}
