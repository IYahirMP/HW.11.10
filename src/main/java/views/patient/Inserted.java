package views.patient;

import com.solvd.laba.computer_repair_service.views.FeedbackView;
import models.Patient;

import java.util.HashMap;

public class Inserted extends FeedbackView {
    public Inserted(HashMap<String, String> inputs){
        super(inputs);
    }

    public Inserted(){
        super(new HashMap<>());
    }

    public void display(){
        Patient patient = (Patient) displayData.get("element");
        System.out.println("The inserted patient is " + patient);
    }

    public HashMap<String, String> getInputs(){
        return this.inputs;
    }
}
