package views.admission_record;

import com.solvd.laba.input.single_input.StringInput;
import com.solvd.laba.view.FeedbackView;

import java.util.HashMap;

public class AdmissionRecordRequestData extends FeedbackView {
    public AdmissionRecordRequestData(HashMap<String, String> inputs){
        super(inputs);
    }

    public AdmissionRecordRequestData(){
        super(new HashMap<>());
    }

    public void display(){
        System.out.println("Please, introduce the admission record's data.");
    }

    public HashMap<String, String> getInputs(){
        StringInput[] stringInputs = {
                new StringInput("patientId",   "Patient id", StringInput.TypeOfString.NUMBER),
                new StringInput("consultationId", "Consultation id", StringInput.TypeOfString.NUMBER),
                new StringInput("admissionDate", "Admission date", StringInput.TypeOfString.DATE),
                new StringInput("dischargeDate", "Discharge date", StringInput.TypeOfString.DATE),
                new StringInput("roomNumber",   "Room number", StringInput.TypeOfString.NUMBER),
                new StringInput("bedNumber",   "Bed number", StringInput.TypeOfString.NUMBER),
        };

        processInputs(stringInputs);

        return inputs;
    }
}
