package dao.mysql.generic;

import dao.interfaces.Crud;

import java.sql.*;
import java.util.*;

import static dao.factories.MySQLDAOFactory.createConnection;
import static dao.mysql.generic.SQLCreator.*;

public class MySQLCrudDAO implements Crud<Object> {
    private String currentStatement = "";

    /**
     * @param obj
     */
    @Override
    public int insert(Object obj) {
        currentStatement = constructInsertStatement(obj);
        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(currentStatement);
            bindInsertStatement(ps, obj);
            return ps.executeUpdate();
        } catch (SQLException | IllegalAccessException e){
            currentStatement = "";
            throw new RuntimeException(e);
        }
    }

    /**
     * @param id
     * @param obj
     */
    @Override
    public int update(int id, Object obj) {
        currentStatement = constructUpdateStatement(obj);

        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(currentStatement);
            bindUpdateStatement(ps, obj, id);
            return ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }

        return -1;
    }

    /**
     * @param id
     */
    public int delete(int id, Object obj) {
        currentStatement = constructDeleteStatement(obj);
        return delete(id);
    }

    @Override
    public int delete(int id) {
        try(Connection con = createConnection()){
            if (currentStatement.contains("DELETE FROM")) {
                PreparedStatement ps = con.prepareStatement(currentStatement);
                bindDeleteStatement(ps, id);
                return ps.executeUpdate();
            }else{
                throw new SQLException("Unexpected SQL Statement");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<Object> select(int id) {
        try(Connection con = createConnection()){
            currentStatement = constructSelectStatement(id);
            PreparedStatement ps = con.prepareStatement(currentStatement);
            bindSelectStatement(ps, id);
            ResultSet rs = ps.executeQuery();

        }catch(SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * @return
     */
    @Override
    public List<Object> selectAll() {
        return List.of();
    }
}
