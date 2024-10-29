package dao.mysql;

import dao.interfaces.MedicineDAO;
import models.Medicine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static dao.factories.MySQLDAOFactory.createConnection;

public class MySQLMedicineDAO implements MedicineDAO {
    /**
     * @param obj
     */
    @Override
    public int insert(Medicine obj) {
        String sql = "insert into medicine (name, cost, doseSize) values(?, ?, ?)";

        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, obj.getName());
            ps.setDouble(2, obj.getCost());
            ps.setInt(3, obj.getDoseSize());

            int affectedRows = ps.executeUpdate();
            return affectedRows;
        }catch(SQLException e){
            e.printStackTrace();
        }

        return -1;
    }

    /**
     * @param id
     * @param obj
     */
    @Override
    public int update(int id, Medicine obj) {
        String sql = "update medicine set " +
                "name = ?," +
                "cost = ?," +
                "doseSize = ?" +
                "where medicineId = ?";

        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, obj.getName());
            ps.setDouble(2, obj.getCost());
            ps.setInt(3, obj.getDoseSize());
            ps.setInt(4, id);

            int affectedRows = ps.executeUpdate();
            return affectedRows;

        }catch(SQLException e){
            e.printStackTrace();
        }

        return -1;
    }

    /**
     * @param id
     */
    @Override
    public int delete(int id) {
        String sql = "delete from medicine where medicineId = ?";
        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected;
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
    public Optional<Medicine> select(int id) {
        String sql = "select * from medicine where medicineId = ?";
        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return Optional.of(constructObject(rs));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return Optional.empty();
    }

    /**
     * @return
     */
    @Override
    public List<Medicine> selectAll() {
        String sql = "select * from medicine";
        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            List<Medicine> list = new ArrayList<>();
            while(rs.next()){
                list.add(constructObject(rs));
            }
            return list;
        }catch(SQLException e){
            e.printStackTrace();
        }

        return List.of();
    }

    public Medicine constructObject(ResultSet rs) throws SQLException {
        return new Medicine()
                .setMedicineId(rs.getInt("medicineId"))
                .setName(rs.getString("name"))
                .setCost(rs.getDouble("cost"))
                .setDoseSize(rs.getInt("doseSize"));
    }
}
