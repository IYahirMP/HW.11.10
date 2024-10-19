package views;

import com.solvd.laba.computer_repair_service.input.single_input.StringInput;
import com.solvd.laba.computer_repair_service.input.single_input.StringInput.TypeOfString;
import com.solvd.laba.computer_repair_service.views.FeedbackView;

import java.util.HashMap;

public final class StaxConfiguration extends FeedbackView {

    public StaxConfiguration(HashMap<String, String> inputs){
        super(inputs);
    }

    public StaxConfiguration(){
        super(new HashMap<>());
    }

    public void display(){
        System.out.println("Please, configure your XML source");
    }

    public HashMap<String, String> getInputs(){
        StringInput[] stringInputs = {
                new StringInput("uri",   "File URI", TypeOfString.FILE_PATH)
        };

        processInputs(stringInputs);
        return inputs;
    }

}
