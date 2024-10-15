package views;

import com.solvd.laba.computer_repair_service.input.single_input.StringInput;
import com.solvd.laba.computer_repair_service.input.single_input.StringInput.TypeOfString;
import com.solvd.laba.computer_repair_service.views.FeedbackView;

import java.util.HashMap;

public final class DataSourceSelection extends FeedbackView {

    public DataSourceSelection(HashMap<String, String> inputs){
        super(inputs);
    }

    public DataSourceSelection(){
        super(new HashMap<>());
    }

    public void display(){
        System.out.println("Welcome!");
        System.out.println("Please, select one data source:");
        System.out.println("1 - MySQL database");
        System.out.println("2 - XML file");
        System.out.println("3 - Exit");
    }

    public HashMap<String, String> getInputs(){
        StringInput[] stringInputs = {
                new StringInput("menuOption",   "Option", TypeOfString.NUMBER),
        };

        processInputs(stringInputs);

        int val = Integer.parseInt(inputs.get("menuOption"));
        if (val < 1 || val > 3){
            System.out.println("Option is not valid, please try again.");
            getInputs();
        };
        return inputs;
    }

}
