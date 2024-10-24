package views.medicine;

import com.solvd.laba.input.single_input.StringInput;
import com.solvd.laba.view.FeedbackView;

import java.util.HashMap;

public class MedicineRequestData extends FeedbackView {
    public MedicineRequestData(HashMap<String, String> inputs){
        super(inputs);
    }

    public MedicineRequestData(){
        super(new HashMap<>());
    }

    public void display(){
        System.out.println("Please, introduce the medicine's data.");
    }

    public HashMap<String, String> getInputs(){
        StringInput[] stringInputs = {
                new StringInput("name",   "Name", StringInput.TypeOfString.NAME),
                new StringInput("cost", "Cost", StringInput.TypeOfString.DECIMAL),
                new StringInput("doseSize", "Size of doze (mg)", StringInput.TypeOfString.NUMBER),
        };

        processInputs(stringInputs);

        return inputs;
    }
}
