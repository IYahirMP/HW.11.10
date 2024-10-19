package views.admission_record.patient;

import com.solvd.laba.computer_repair_service.views.FeedbackView;
import models.Patient;

import java.util.HashMap;

public class Updated extends FeedbackView {
    public Updated(HashMap<String, String> inputs){
        super(inputs);
    }

    public Updated(){
        super(new HashMap<>());
    }

    public void display(){
        int updated = (Integer) displayData.get("updatedRows");
        int updatedId = (Integer) displayData.get("updatedId");
        Patient patient = (Patient) displayData.get("patient");
        patient.setPatientId(updatedId);

        if (updated == 1){
            System.out.println("Patient with id " + updatedId + " has been updated successfully.");
            System.out.print("New data is as follows:");
            System.out.println(patient);
        } else if(updated == 0){
            System.out.println("Patient with id " + updatedId + " couldn't be updated.");
        } else{
            System.out.println("There was an unknown error during update of Patient with id " + updatedId + ".");
        }
    }

    public HashMap<String, String> getInputs(){
        return this.inputs;
    }
}
