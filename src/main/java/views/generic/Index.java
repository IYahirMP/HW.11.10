package views.generic;

import com.solvd.laba.view.FeedbackView;

import java.util.HashMap;
import java.util.List;

public class Index<T> extends FeedbackView {
    public Index(HashMap<String, String> inputs){
        super(inputs);
    }

    public Index(){
        super(new HashMap<>());
    }

    public void display(){
        List<T> elems = (List<T>) displayData.get("element");

        if(elems.isEmpty()){
            System.out.println("No elements found");
            return;
        }

        System.out.println("List of elements");
        for(T p: elems){
            System.out.println(p.toString());
        }
    }

    public HashMap<String, String> getInputs(){
        return this.inputs;
    }
}
