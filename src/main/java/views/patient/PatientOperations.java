package views.patient;

import com.solvd.laba.computer_repair_service.input.single_input.StringInput;
import com.solvd.laba.computer_repair_service.input.single_input.StringInput.TypeOfString;
import com.solvd.laba.computer_repair_service.views.FeedbackView;

import java.util.HashMap;

public final class PatientOperations extends FeedbackView {

    public PatientOperations(HashMap<String, String> inputs){
        super(inputs);
    }

    public PatientOperations(){
        super(new HashMap<>());
    }

    public void display(){
        System.out.println("Supported operations for Patient entity are:");
        System.out.println("1 - Show [requires Patient ID]");
        System.out.println("2 - Insert [requires input]");
        System.out.println("3 - Index");
        System.out.println("4 - Delete [requires Patient ID]");
        System.out.println("5 - Update [requires Patient ID and Patient data]");
        System.out.println("6 - Exit");
    }

    public HashMap<String, String> getInputs(){
        StringInput[] stringInputs = {
                new StringInput("menuOption",   "Option", TypeOfString.NUMBER),
        };

        processInputs(stringInputs);

        int val = Integer.parseInt(inputs.get("menuOption"));
        if (val < 1 || val > 6){
            System.out.println("Option is not valid, please try again.");
            getInputs();
        };
        return inputs;
    }

}
