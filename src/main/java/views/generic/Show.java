package views.generic;

import com.solvd.laba.view.FeedbackView;

import java.util.HashMap;

public class Show<T> extends FeedbackView {
    public Show(HashMap<String, String> inputs){
        super(inputs);
    }

    public Show(){
        super(new HashMap<>());
    }

    public void display(){
        T elem = (T) displayData.get("element");
        System.out.printf("The %s is %s",elem.getClass().getSimpleName(),elem);
    }

    public HashMap<String, String> getInputs(){
        return this.inputs;
    }
}
