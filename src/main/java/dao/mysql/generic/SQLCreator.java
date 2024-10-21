package dao.mysql.generic;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SQLCreator {

    public static String constructInsertStatement(Object obj)
    {
        ClassAnalyzer os = new ClassAnalyzer(obj);
        int max = os.getFieldNames().size() - 1;
        List<String> fields = os.getFieldNames((a) -> !a.contains("Id"));

        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(os.getClassName());
        sb.append("(");
        for(int i = 0; i < max; i++){
            sb.append(fields.get(i));
            if(i != max-1){
                sb.append(",");
            }
        }
        sb.append(") VALUES (");
        for(int i = 0; i < max; i++){
            sb.append("?");
            if(i != max-1){
                sb.append(",");
            }
        }
        sb.append(");");

        return sb.toString();
    }

    public static String constructUpdateStatement(Object obj){
        ClassAnalyzer os = new ClassAnalyzer(obj);
        //Filter out Ids
        List<String> fields = os.getFieldNames((a) -> !a.contains("Id"));
        String idField = os.getClassName() + "Id";


        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(os.getClassName());
        sb.append(" SET ");

        int max = fields.size();
        for (int i = 0; i < max; i++) {
            String fieldName = fields.get(i);
            sb.append(fieldName).append(" = ?");
            if (i != max - 1) {
                sb.append(", ");
            }
        }
        sb.append(" WHERE " + idField +  " = ?");
        return sb.toString();
    }

    public static String constructDeleteStatement(Object obj){
        ClassAnalyzer os = new ClassAnalyzer(obj);
        String idField = os.getClassName() + "Id";

        String sql = "DELETE FROM " + os.getClassName() + " WHERE " + idField + " = ?;";
        return sql;
    }

    public static String constructSelectStatement(Object obj){
        ClassAnalyzer os = new ClassAnalyzer(obj);
        String idField = os.getClassName() + "Id";

        String sql = "SELECT * FROM " + os.getClassName() + " WHERE " + idField + " = ?;";
        return sql;
    }

    public static String constructSelectAllStatement(Object obj){
        ClassAnalyzer os = new ClassAnalyzer(obj);

        String sql = "SELECT * FROM " + os.getClassName() + ";";
        return sql;
    }


    public static void bindInsertStatement(PreparedStatement ps, Object obj) throws SQLException, IllegalAccessException{
        ClassAnalyzer os = new ClassAnalyzer(obj);
        int max = os.getFieldNames().size() - 1;
        List<Field> fields = os.getFields().stream().filter((field) -> !field.getName().contains("Id")).toList();

        for(int i = 0; i < max; i++){
            Field field = fields.get(i);
            field.setAccessible(true);
            Object val = field.get(obj);
            switch(field.getType().getSimpleName()){
                case "String": ps.setString(i+1, (String) val); break;
                case "Integer", "int": ps.setInt(i+1, (Integer) val); break;
                case "Double", "double": ps.setDouble(i+1, (Double) val); break;
                case "Long", "long": ps.setLong(i+1, (Long) val); break;
                case "Boolean", "boolean": ps.setBoolean(i+1, (Boolean) val); break;
                case "Date", "DateTime": ps.setDate(i+1, (Date) val); break;
                case "Object": ps.setObject(i+1, (Object) val); break;
            }
            field.setAccessible(false);
        }
    }

    public static void bindUpdateStatement(PreparedStatement ps, Object obj, int id) throws SQLException, IllegalAccessException{
        bindInsertStatement(ps, obj);
        int max = new ClassAnalyzer(obj).getFieldNames().size();
        ps.setInt(max, id);
    }

    public static void bindDeleteStatement(PreparedStatement ps, int id) throws SQLException{
        ps.setInt(1, id);
    }

    public static void bindSelectStatement(PreparedStatement ps, int id) throws SQLException{
        ps.setInt(1, id);
    }
}
