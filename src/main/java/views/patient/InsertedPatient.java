package views.patient;

import com.solvd.laba.computer_repair_service.views.FeedbackView;
import models.Patient;

import java.util.HashMap;

public class InsertedPatient extends FeedbackView {
    public InsertedPatient(HashMap<String, String> inputs){
        super(inputs);
    }

    public InsertedPatient(){
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
