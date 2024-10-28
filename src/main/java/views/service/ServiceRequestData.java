package views.service;

import com.solvd.laba.input.single_input.StringInput;
import com.solvd.laba.view.FeedbackView;

import java.util.HashMap;

public class ServiceRequestData extends FeedbackView {
    public ServiceRequestData(HashMap<String, String> inputs){
        super(inputs);
    }

    public ServiceRequestData(){
        super(new HashMap<>());
    }

    public void display(){
        System.out.println("Please, introduce the service's data.");
    }

    public HashMap<String, String> getInputs(){
        StringInput[] stringInputs = {
                new StringInput("description",   "Service description", StringInput.TypeOfString.LARGE_INPUT),
                new StringInput("cost", "Service cost", StringInput.TypeOfString.DECIMAL),
        };

        processInputs(stringInputs);

        return inputs;
    }
}
