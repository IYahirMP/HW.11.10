package views.doctor;

import com.solvd.laba.computer_repair_service.input.single_input.StringInput;
import com.solvd.laba.computer_repair_service.views.FeedbackView;

import java.util.HashMap;

public class DoctorRequestData extends FeedbackView {
    public DoctorRequestData(HashMap<String, String> inputs){
        super(inputs);
    }

    public DoctorRequestData(){
        super(new HashMap<>());
    }

    public void display(){
        System.out.println("Please, introduce the doctor's data.");
    }

    public HashMap<String, String> getInputs(){
        StringInput[] stringInputs = {
                new StringInput("name",   "Doctor's name", StringInput.TypeOfString.NAME),
                new StringInput("phone", "Doctor's phone", StringInput.TypeOfString.PHONE),
        };

        processInputs(stringInputs);

        return inputs;
    }
}
