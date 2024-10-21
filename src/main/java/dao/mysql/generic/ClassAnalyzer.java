package dao.mysql.generic;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class ClassAnalyzer {
    private Class objectClass = null;
    private String className = null;
    private Field[] fields = null;

    public ClassAnalyzer(Object obj){
        objectClass = obj.getClass();
        className = objectClass.getSimpleName();
        fields = objectClass.getDeclaredFields();
    }

    public Class getObjectClass() {
        return objectClass;
    }

    public String getClassName() {
        return className;
    }

    public List<Field> getFields() {
        return new ArrayList<>(Arrays.asList(fields));
    }

    public List<Field> getFields(Predicate<Field> predicate){
        return getFields().stream().filter(predicate).toList();
    }

    public List<String> getFieldNames(){
        List<String> fieldNames = new ArrayList<>();
        for(Field field : fields){
            fieldNames.add(field.getName());
        }
        return fieldNames;
    }

    public List<String> getFieldNames(Predicate<String> predicate){
        return getFieldNames().stream().filter(predicate).toList();
    }

    public Object createInstance(Object obj) throws Exception{
        Class objClass = obj.getClass();
        Constructor constructor = objClass.getDeclaredConstructor();
        Object constructorObj = constructor.newInstance();

        return constructorObj;
    }

    public void assignValuesToObject(Object obj, List<Object> value){
        //1. Retrieve the objects attributes
        //2. For each attribute
        //  2.1 Filter out methods that match attribute name and map them.
        //  2.2 Map attribute to SQL get method
        //  2.3 Invoke mapped setMethod with mapped SQL get method output

        ClassAnalyzer classAnalyzer = new ClassAnalyzer(obj);
        List<Field> fields = classAnalyzer.getFields();

        for(Field field : fields){
            List<Method> methods = mapAttributesToSetMethods(obj);
            List<Method> sqlMethods = mapAttributesToSQLGetMethods(obj);
        }
    }

    public List<Method> mapAttributesToSetMethods(Object obj){
        List<Method> methods = new ArrayList<>();
        List<Method> allMethods = Arrays.asList(obj.getClass().getDeclaredMethods());

        ClassAnalyzer classAnalyzer = new ClassAnalyzer(obj);
        List<Field> fields = classAnalyzer.getFields();
        for(Field field : fields){
            methods.add(allMethods.stream().
                    filter(method -> method.getName().toLowerCase()
                            .contains(field.getName().toLowerCase()))
                    .findFirst().get());
        }

        return methods;
    }

    public List<Method> mapAttributesToSQLGetMethods(Object obj){
        List<Method> methods = new ArrayList<>();
        List<Method> allMethods = Arrays.asList(PreparedStatement.class.getDeclaredMethods());

        ClassAnalyzer classAnalyzer = new ClassAnalyzer(obj);
        List<Field> fields = classAnalyzer.getFields();

        for(Field field : fields){
            methods.add(allMethods.stream().
                    filter(method -> method.getName().toLowerCase()
                            .contains(field.getName().toLowerCase()))
                    .findFirst().get());
        }

        return methods;
    }
}
