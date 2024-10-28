package views;

import com.solvd.laba.input.single_input.StringInput;
import com.solvd.laba.input.single_input.StringInput.TypeOfString;
import com.solvd.laba.view.FeedbackView;

import java.util.HashMap;

public final class XMLFileConfiguration extends FeedbackView {

    public XMLFileConfiguration(HashMap<String, String> inputs){
        super(inputs);
    }

    public XMLFileConfiguration(){
        super(new HashMap<>());
    }

    public void display(){
        System.out.println("Please, configure your XML source");
    }

    public HashMap<String, String> getInputs(){
        StringInput[] stringInputs = {
                new StringInput("uri",   "File URI", TypeOfString.FILE_PATH)
        };

        processInputs(stringInputs);
        return inputs;
    }

}
