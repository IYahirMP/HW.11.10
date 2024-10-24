package views.consultation;

import com.solvd.laba.computer_repair_service.input.single_input.StringInput;
import com.solvd.laba.computer_repair_service.views.FeedbackView;

import java.util.HashMap;

public class ConsultationRequestData extends FeedbackView {
    public ConsultationRequestData(HashMap<String, String> inputs){
        super(inputs);
    }

    public ConsultationRequestData(){
        super(new HashMap<>());
    }

    public void display(){
        System.out.println("Please, introduce the consultation's data.");
    }

    public HashMap<String, String> getInputs(){
        StringInput[] stringInputs = {
                new StringInput("date",   "Date", StringInput.TypeOfString.DATE),
                new StringInput("doctorId", "Doctor id", StringInput.TypeOfString.NUMBER),
                new StringInput("patientId", "Patient id", StringInput.TypeOfString.NUMBER),
                new StringInput("diagnose", "Diagnose", StringInput.TypeOfString.LARGE_INPUT),
                new StringInput("prescriptionId",   "Prescription id", StringInput.TypeOfString.NUMBER),
                new StringInput("admittedForTreatment",   "Is admitted for treatment (n>0 = yes; n<1 = no)", StringInput.TypeOfString.NUMBER)
        };

        processInputs(stringInputs);

        return inputs;
    }
}
