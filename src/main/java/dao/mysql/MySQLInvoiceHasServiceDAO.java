package dao.mysql;

import dao.InvoiceHasServiceDAO;
import models.InvoiceHasService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static dao.factories.MySQLDAOFactory.createConnection;

public class MySQLInvoiceHasServiceDAO implements InvoiceHasServiceDAO {
    /**
     * @param obj
     */
    @Override
    public int insert(InvoiceHasService obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @param id
     * @param obj
     */
    @Override
    public int update(int id, InvoiceHasService obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @param id
     */
    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int delete(int id1, int id2) {
        String sql = "delete from InvoiceHasService where invoiceId = ? AND serviceId = ?";
        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id1);
            ps.setInt(2, id2);

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
    public Optional<InvoiceHasService> select(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Optional<InvoiceHasService> select(int invoiceId, int serviceId) {

        String sql = "select * from InvoiceHasService where invoiceId = ? AND serviceId = ?";
        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, invoiceId);
            ps.setInt(2, serviceId);

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
    public List<InvoiceHasService> selectAll() {
        String sql = "select * from InvoiceHasService";
        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            List<InvoiceHasService> list = new ArrayList<>();
            while(rs.next()){
                list.add(constructObject(rs));
            }
            return list;
        }catch(SQLException e){
            e.printStackTrace();
        }

        return List.of();
    }

    public InvoiceHasService constructObject(ResultSet rs) throws SQLException {
        return new InvoiceHasService()
                .setLineCost(rs.getDouble("LineCost"))
                .setInvoiceId(rs.getInt("InvoiceId"))
                .setServiceId(rs.getInt("ServiceId"))
                .setQuantity(rs.getInt("Quantity"));
    }
}
