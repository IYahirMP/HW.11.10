package views.generic;

import com.solvd.laba.view.FeedbackView;
import models.Patient;

import java.util.HashMap;

public class Inserted<T> extends FeedbackView {
    public Inserted(HashMap<String, String> inputs){
        super(inputs);
    }

    public Inserted(){
        super(new HashMap<>());
    }

    public void display(){
        T elem = (T) displayData.get("element");
        System.out.printf("The inserted %s is %s\n", elem.getClass().getName(), elem);
    }

    public HashMap<String, String> getInputs(){
        return this.inputs;
    }
}
