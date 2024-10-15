package views.patient;

import com.solvd.laba.computer_repair_service.views.FeedbackView;
import models.Patient;

import java.util.HashMap;

public class ShowPatient extends FeedbackView {
    public ShowPatient(HashMap<String, String> inputs){
        super(inputs);
    }

    public ShowPatient(){
        super(new HashMap<>());
    }

    public void display(){
        Patient patient = (Patient) displayData.get("element");
        System.out.println("The patient is " + patient);
    }

    public HashMap<String, String> getInputs(){
        return this.inputs;
    }
}
