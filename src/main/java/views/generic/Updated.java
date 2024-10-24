package views.generic;

import com.solvd.laba.view.FeedbackView;
import models.Patient;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class Updated<T> extends FeedbackView {
    public Updated(HashMap<String, String> inputs){
        super(inputs);
    }

    public Updated(){
        super(new HashMap<>());
    }

    public void display(){
        int updated = (Integer) displayData.get("updatedRows");
        int updatedId = (Integer) displayData.get("updatedId");
        T elem = (T) displayData.get("element");

        if (updated == 1){
            changeIdAccessor(elem, updatedId);
            System.out.printf("%s with id %s has been updated successfully.", elem.getClass().getSimpleName(), updatedId);
            System.out.print("New data is as follows:");
            System.out.println(elem);
        } else if(updated == 0){
            System.out.printf("%s with id %s couldn't be updated.", elem.getClass().getSimpleName(), updatedId);
        } else{
            System.out.printf("There was an unknown error during update of %s with id %s.", elem.getClass().getSimpleName(), updatedId );
        }
    }

    private void changeIdAccessor(T elem, int updatedId){
        try {
            Class elemClass = elem.getClass();
            List<Field> fields = Arrays.stream(elemClass.getDeclaredFields()).toList();
            Optional<Field> idField = fields.stream().filter(f -> f.getName().contains("Id")).findFirst();
            if (idField.isPresent()) {
                idField.get().setAccessible(true);
                idField.get().set(elem, updatedId);
                idField.get().setAccessible(false);
            }

        }catch(Exception e){
            System.out.println("Error while accessing updated object properties.");
            e.printStackTrace();
        }
    }

    public HashMap<String, String> getInputs(){
        return this.inputs;
    }
}
