package views.treatment_record;

import com.solvd.laba.input.single_input.StringInput;
import com.solvd.laba.view.FeedbackView;

import java.util.HashMap;

public class TreatmentRecordRequestData extends FeedbackView {
    public TreatmentRecordRequestData(HashMap<String, String> inputs){
        super(inputs);
    }

    public TreatmentRecordRequestData(){
        super(new HashMap<>());
    }

    public void display(){
        System.out.println("Please, introduce the record's data.");
    }

    public HashMap<String, String> getInputs(){
        StringInput[] stringInputs = {
                new StringInput("admissionId",   "Associated admission Id", StringInput.TypeOfString.NUMBER),
                new StringInput("time", "Time of capture", StringInput.TypeOfString.DATETIME),
                new StringInput("notes", "Notes", StringInput.TypeOfString.LARGE_INPUT),
        };

        processInputs(stringInputs);

        return inputs;
    }
}
