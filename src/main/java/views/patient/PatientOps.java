package views.patient;

import com.solvd.laba.computer_repair_service.input.single_input.StringInput;
import com.solvd.laba.computer_repair_service.input.single_input.StringInput.TypeOfString;
import com.solvd.laba.computer_repair_service.views.FeedbackView;

import java.util.HashMap;

public final class PatientOps extends FeedbackView {

    public PatientOps(HashMap<String, String> inputs){
        super(inputs);
    }

    public PatientOps(){
        super(new HashMap<>());
    }

    public void display(){
        System.out.println("Supported operations for Patient entity are:");
        System.out.println("1 - Show [requires Patient ID]");
        System.out.println("2 - Insert [requires input]");
        System.out.println("3 - Index");
        System.out.println("4 - Exit");
    }

    public HashMap<String, String> getInputs(){
        StringInput[] stringInputs = {
                new StringInput("menuOption",   "Option", TypeOfString.NUMBER),
        };

        processInputs(stringInputs);

        int val = Integer.parseInt(inputs.get("menuOption"));
        if (val < 1 || val > 4){
            System.out.println("Option is not valid, please try again.");
            getInputs();
        };
        return inputs;
    }

}
