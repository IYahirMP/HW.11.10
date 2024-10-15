package views.patient;

import com.solvd.laba.computer_repair_service.input.single_input.StringInput;
import com.solvd.laba.computer_repair_service.views.FeedbackView;

import java.util.HashMap;

public class RequestPatientId extends FeedbackView {
    public RequestPatientId(HashMap<String, String> inputs){
        super(inputs);
    }

    public RequestPatientId(){
        super(new HashMap<>());
    }

    public void display(){
        System.out.println("Please, introduce the patient's id.");
    }

    public HashMap<String, String> getInputs(){
        StringInput[] stringInputs = {
                new StringInput("id",   "Patient id (integer):", StringInput.TypeOfString.NUMBER),
        };

        processInputs(stringInputs);

        return inputs;
    }
}
