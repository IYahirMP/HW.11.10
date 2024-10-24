package views.consultation;

import com.solvd.laba.input.single_input.StringInput;
import com.solvd.laba.input.single_input.StringInput.TypeOfString;
import com.solvd.laba.view.FeedbackView;

import java.util.HashMap;

public final class ConsultationOperations extends FeedbackView {

    public ConsultationOperations(HashMap<String, String> inputs){
        super(inputs);
    }

    public ConsultationOperations(){
        super(new HashMap<>());
    }

    public void display(){
        System.out.println("Supported operations for Consultation entity are:");
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
        }
        return inputs;
    }

}
