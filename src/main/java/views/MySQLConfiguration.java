package views;

import com.solvd.laba.input.single_input.StringInput;
import com.solvd.laba.input.single_input.StringInput.TypeOfString;
import com.solvd.laba.view.FeedbackView;

import java.util.HashMap;

public final class MySQLConfiguration extends FeedbackView {

    public MySQLConfiguration(HashMap<String, String> inputs){
        super(inputs);
    }

    public MySQLConfiguration(){
        super(new HashMap<>());
    }

    public void display(){
        System.out.println("Please, configure your MySQL database");
    }

    public HashMap<String, String> getInputs(){
        StringInput[] stringInputs = {
                new StringInput("url",   "URL", TypeOfString.URL),
                new StringInput("database", "Database name", TypeOfString.WORD),
                new StringInput("username", "User name", TypeOfString.USERNAME),
                new StringInput("password", "Password", TypeOfString.PASSWORD)
        };

        processInputs(stringInputs);
        return inputs;
    }

}
