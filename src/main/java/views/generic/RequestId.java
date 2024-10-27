package views.generic;

import com.solvd.laba.input.single_input.StringInput;
import com.solvd.laba.view.FeedbackView;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;

public class RequestId extends FeedbackView {
    private String className = "";

    public RequestId(HashMap<String, String> inputs, String className){
        super(inputs);
        this.className = className;
    }
    public RequestId(String className){
        super(new HashMap<String, String>());
        this.className = className;
    }

    public void display(){
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        System.out.printf("Please, introduce the %s's id.\n", className);
    }

    public HashMap<String, String> getInputs(){
        String displayName = className + " id (integer)";
        StringInput[] stringInputs = {
                new StringInput("id",   displayName, StringInput.TypeOfString.NUMBER),
        };

        processInputs(stringInputs);

        return inputs;
    }
}
