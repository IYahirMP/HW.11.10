package views.emergency_contact;

import com.solvd.laba.computer_repair_service.input.single_input.StringInput;
import com.solvd.laba.computer_repair_service.views.FeedbackView;

import java.util.HashMap;

public class EmergencyContactRequestData extends FeedbackView {
    public EmergencyContactRequestData(HashMap<String, String> inputs){
        super(inputs);
    }

    public EmergencyContactRequestData(){
        super(new HashMap<>());
    }

    public void display(){
        System.out.println("Please, introduce the patient's data.");
    }

    public HashMap<String, String> getInputs(){
        StringInput[] stringInputs = {
                new StringInput("name",   "Contact's name", StringInput.TypeOfString.NAME),
                new StringInput("patientId", "Associated patient id", StringInput.TypeOfString.NUMBER),
                new StringInput("address", "Contact's address", StringInput.TypeOfString.ADDRESS),
                new StringInput("phone", "Contact's phone", StringInput.TypeOfString.PHONE),
        };

        processInputs(stringInputs);

        return inputs;
    }
}
