package views.prescription;

import com.solvd.laba.input.single_input.StringInput;
import com.solvd.laba.view.FeedbackView;

import java.util.HashMap;

public class PrescriptionRequestData extends FeedbackView {
    public PrescriptionRequestData(HashMap<String, String> inputs){
        super(inputs);
    }

    public PrescriptionRequestData(){
        super(new HashMap<>());
    }

    public void display(){
        System.out.println("Please, introduce the prescription's data.");
    }

    public HashMap<String, String> getInputs(){
        StringInput[] stringInputs = {
                new StringInput("patientId",   "Patient's id", StringInput.TypeOfString.NUMBER),
                new StringInput("diagnose", "Diagnose", StringInput.TypeOfString.LARGE_INPUT),
        };

        processInputs(stringInputs);

        return inputs;
    }
}
