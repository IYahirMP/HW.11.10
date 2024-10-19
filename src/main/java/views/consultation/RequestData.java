package views.consultation;

import com.solvd.laba.computer_repair_service.input.single_input.StringInput;
import com.solvd.laba.computer_repair_service.views.FeedbackView;

import java.util.HashMap;

public class RequestData extends FeedbackView {
    public RequestData(HashMap<String, String> inputs){
        super(inputs);
    }

    public RequestData(){
        super(new HashMap<>());
    }

    public void display(){
        System.out.println("Please, introduce the patient's data.");
    }

    public HashMap<String, String> getInputs(){
        StringInput[] stringInputs = {
                new StringInput("name",   "Patient's name", StringInput.TypeOfString.NAME),
                new StringInput("age", "Patient's age", StringInput.TypeOfString.NUMBER),
                new StringInput("address", "Patient's address", StringInput.TypeOfString.ADDRESS),
                new StringInput("phone", "Patient's phone", StringInput.TypeOfString.PHONE),
        };

        processInputs(stringInputs);

        return inputs;
    }
}
