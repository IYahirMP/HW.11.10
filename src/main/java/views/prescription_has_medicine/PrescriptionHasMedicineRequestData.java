package views.prescription_has_medicine;

import com.solvd.laba.input.single_input.StringInput;
import com.solvd.laba.view.FeedbackView;

import java.util.HashMap;

public class PrescriptionHasMedicineRequestData extends FeedbackView {
    public PrescriptionHasMedicineRequestData(HashMap<String, String> inputs){
        super(inputs);
    }

    public PrescriptionHasMedicineRequestData(){
        super(new HashMap<>());
    }

    public void display(){
        System.out.println("Please introduce data to associate an invoice with a service.");
    }

    public HashMap<String, String> getInputs(){
        StringInput[] stringInputs = {
                new StringInput("prescriptionId",   "Prescription id", StringInput.TypeOfString.NUMBER),
                new StringInput("medicineId", "Medicine id", StringInput.TypeOfString.NUMBER),
                new StringInput("prescribedDays", "Prescribed days", StringInput.TypeOfString.NUMBER),
                new StringInput("prescribedDose", "Prescribed dose (mg)", StringInput.TypeOfString.NUMBER),
                new StringInput("prescribedTiming", "Timing for medication", StringInput.TypeOfString.LARGE_INPUT),
        };

        processInputs(stringInputs);

        return inputs;
    }
}
