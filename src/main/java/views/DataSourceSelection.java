package views;

import com.solvd.laba.input.single_input.StringInput;
import com.solvd.laba.input.single_input.StringInput.TypeOfString;
import com.solvd.laba.view.FeedbackView;

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
        System.out.println("2 - MySQL database (MyBatis)");
        System.out.println("3 - XML file (Parse using StAX)");
        System.out.println("4 - XML file (Parse using JAXB)");
        System.out.println("5 - JSON file (Parse using Jackson)");
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
