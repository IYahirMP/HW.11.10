package views.patient;

import com.solvd.laba.computer_repair_service.views.FeedbackView;
import models.Patient;

import java.util.HashMap;

public class Deleted extends FeedbackView {
    public Deleted(HashMap<String, String> inputs){
        super(inputs);
    }

    public Deleted(){
        super(new HashMap<>());
    }

    public void display(){
        int deleted = (Integer) displayData.get("deletedRows");
        int deletedId = (Integer) displayData.get("deletedId");

        if (deleted == 1){
            System.out.println("Patient with id " + deletedId + " has been deleted successfully.");
        } else if(deleted == 0){
            System.out.println("Patient with id " + deletedId + " couldn't be deleted.");
        } else{
            System.out.println("There was an unknown error during deletion of Patient with id " + deletedId + ".");
        }
    }

    public HashMap<String, String> getInputs(){
        return this.inputs;
    }
}
