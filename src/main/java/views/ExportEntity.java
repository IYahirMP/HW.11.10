package views;

import com.solvd.laba.input.single_input.StringInput;
import com.solvd.laba.input.single_input.StringInput.TypeOfString;
import com.solvd.laba.view.FeedbackView;

import java.util.HashMap;

public final class ExportEntity extends FeedbackView {

    public ExportEntity(HashMap<String, String> inputs){
        super(inputs);
    }

    public ExportEntity(){
        super(new HashMap<>());
    }

    public void display(){
        System.out.println("Please, select the table you want to export:");
        System.out.println("1 - Patient");
        System.out.println("2 - EmergencyContact");
        System.out.println("3 - Doctor");
        System.out.println("4 - Consultation");
        System.out.println("5 - Prescription");
        System.out.println("6 - Medicine");
        System.out.println("7 - AdmissionRecord");
        System.out.println("8 - TreatmentRecord");
        System.out.println("9 - Invoice");
        System.out.println("10 - Service");
        System.out.println("11 - InvoiceHasService");
        System.out.println("12 - PrescriptionHasMedicine");
        System.out.println("13 - All tables");
        System.out.println("14 - Exit program");
    }

    public HashMap<String, String> getInputs(){
        StringInput[] stringInputs = {
                new StringInput("menuOption",   "Option", TypeOfString.NUMBER),
        };

        processInputs(stringInputs);

        int val = Integer.parseInt(inputs.get("menuOption"));
        if (val < 1 || val > 14){
            System.out.println("Option is not valid, please try again.");
            getInputs();
        }
        return inputs;
    }

}
