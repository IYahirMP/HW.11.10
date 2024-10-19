package views.generic;

import com.solvd.laba.computer_repair_service.views.FeedbackView;

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
        String deletedType = (String) displayData.get("deletedType");

        if (deleted == 1){
            System.out.printf("%s with id %d has been deleted successfully.\n", deletedType, deletedId);
        } else if(deleted == 0){
            System.out.printf("%s with id %d could not be deleted.\n", deletedType, deletedId);
        } else{
            System.out.printf("There was an unknown error during deletion of %s with id %d.\n", deletedType, deletedId);
        }
    }

    public HashMap<String, String> getInputs(){
        return this.inputs;
    }
}
