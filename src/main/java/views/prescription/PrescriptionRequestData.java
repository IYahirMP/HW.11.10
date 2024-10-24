package views.prescription;

import com.solvd.laba.computer_repair_service.input.single_input.StringInput;
import com.solvd.laba.computer_repair_service.views.FeedbackView;

import java.util.HashMap;

public class PrescriptionRequestData extends FeedbackView {
    public PrescriptionRequestData(HashMap<String, String> inputs){
        super(inputs);
    }

    public PrescriptionRequestData(){
        super(new HashMap<>());
    }

    public void display(){
        System.out.println("Please, introduce the patient's data.");
    }

    public HashMap<String, String> getInputs(){
        StringInput[] stringInputs = {
                new StringInput("patientId",   "Patient's id", StringInput.TypeOfString.NUMBER),
                new StringInput("diagnose", "Diagnose", StringInput.TypeOfString.NUMBER),
        };

        processInputs(stringInputs);

        return inputs;
    }
}
