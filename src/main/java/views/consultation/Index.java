package views.consultation;

import com.solvd.laba.computer_repair_service.views.FeedbackView;
import models.Patient;

import java.util.HashMap;
import java.util.List;

public class Index extends FeedbackView {
    public Index(HashMap<String, String> inputs){
        super(inputs);
    }

    public Index(){
        super(new HashMap<>());
    }

    public void display(){
        List<Patient> patient = (List<Patient>) displayData.get("element");

        if(patient.isEmpty()){
            System.out.println("No patients found");
            return;
        }

        System.out.println("Index of patients");
        for(Patient p: patient){
            System.out.println(p.toString());
        }
    }

    public HashMap<String, String> getInputs(){
        return this.inputs;
    }
}
