package dao.mysql;

import dao.Crud;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static dao.factories.MySQLDAOFactory.createConnection;

public class MySQLCrudDAO implements Crud<Object> {


    private void getObjectData(Object obj){

    }

    /**
     * @param obj
     */
    @Override
    public int insert(Object obj) {
        ObjectStructure os = new ObjectStructure(obj);

        int max = os.getFieldNames().size();

        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(os.getClassName());
        sb.append("(");
        for(int i = 0; i < max; i++){
            sb.append(os.getFieldNames().get(i));
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

        String sql = sb.toString();


        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            for(int i = 0; i < max; i++){
                List<Field> fields = os.getFields();
                for(Field field : fields){
                    Object val = field.get(obj);
                    switch(field.getClass().getSimpleName()){
                        case "String": ps.setString(i+1, (String) val); break;
                        case "Integer": ps.setInt(i+1, (Integer) val); break;
                        case "Double": ps.setDouble(i+1, (Double) val); break;
                        case "Long": ps.setLong(i+1, (Long) val); break;
                        case "Boolean": ps.setBoolean(i+1, (Boolean) val); break;
                        case "Date": ps.setDate(i+1, (Date) val); break;
                        case "Object": ps.setObject(i+1, (Object) val); break;
                    }
                }
            }

            int affectedRows = ps.executeUpdate();
            return affectedRows;
        }catch(SQLException e){
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return -1;
    }

    /**
     * @param id
     * @param obj
     */
    @Override
    public int update(int id, Object obj) {
        String sql = "update Object set " +
                "name = ?," +
                "age = ?," +
                "address = ?," +
                "phone = ? " +
                "where patientId = ?";

       /* try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, obj.getName());
            ps.setInt(2, obj.getAge());
            ps.setString(3, obj.getAddress());
            ps.setString(4, obj.getPhone());
            ps.setInt(5, id);

            int affectedRows = ps.executeUpdate();
            return affectedRows;

        }catch(SQLException e){
            e.printStackTrace();
        }*/

        return -1;
    }

    /**
     * @param id
     */
    @Override
    public int delete(int id) {/*
        String sql = "delete from patient where patientId = ?";
        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected;
        }catch(SQLException e){
            e.printStackTrace();
        }*/

        return -1;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<Object> select(int id) {/*
        String sql = "select * from patient where patientId = ?";
        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Object patient = new Object();
                patient.setObjectId(rs.getInt("patientId"));
                patient.setName(rs.getString("name"));
                patient.setAddress(rs.getString("address"));
                patient.setPhone(rs.getString("phone"));
                return Optional.of(patient);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }*/

        return Optional.empty();
    }

    /**
     * @return
     */
    @Override
    public List<Object> selectAll() {/*
        String sql = "select * from patient";
        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            List<Object> list = new ArrayList<>();
            while(rs.next()){
                Object patient = new Object();
                patient.setObjectId(rs.getInt("patientId"));
                patient.setName(rs.getString("name"));
                patient.setAddress(rs.getString("address"));
                patient.setPhone(rs.getString("phone"));
                list.add(patient);
            }
            return list;
        }catch(SQLException e){
            e.printStackTrace();
        }
*/
        return List.of();
    }

    protected class ObjectStructure {
        private Class objectClass = null;
        private String className = null;
        private Field[] fields = null;

        public ObjectStructure(Object obj){
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

        public List<String> getFieldNames(){
            List<String> fieldNames = new ArrayList<>();
            for(Field field : fields){
                fieldNames.add(field.getName());
            }
            return fieldNames;
        }
    }
}
