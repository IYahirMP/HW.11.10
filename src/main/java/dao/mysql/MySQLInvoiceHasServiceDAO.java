package dao.mysql;

import dao.interfaces.InvoiceHasServiceDAO;
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
     * @return
     */
    @Override
    public int insert(InvoiceHasService obj) {
        String sql = "insert into invoicehasservice (invoiceId, serviceId, quantity, lineCost) values(?, ?, ?, ?)";

        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, obj.getInvoiceId());
            ps.setInt(2, obj.getServiceId());
            ps.setInt(3, obj.getQuantity());
            ps.setDouble(4, obj.getLineCost());

            int affectedRows = ps.executeUpdate();
            return affectedRows;
        }catch(SQLException e){
            e.printStackTrace();
        }

        return -1;
    }

    /**
     * @param invoiceId
     * @param serviceId
     * @return
     */
    @Override
    public int delete(int invoiceId, int serviceId) {
        String sql = "delete from invoicehasservice where invoiceId = ? and serviceId = ?";
        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, invoiceId);
            ps.setInt(2, serviceId);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected;
        }catch(SQLException e){
            e.printStackTrace();
        }

        return -1;
    }

    /**
     * @param invoiceId
     * @param serviceId
     * @return
     */
    @Override
    public Optional<InvoiceHasService> select(int invoiceId, int serviceId) {
        String sql = "select * from invoicehasservice where invoiceId = ? and serviceId = ?";
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
        String sql = "select * from invoicehasservice";
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

    /**
     * @param invoiceId
     * @return
     */
    @Override
    public List<InvoiceHasService> selectByInvoice(int invoiceId) {
        String sql = "select * from invoicehasservice where invoiceId = ?";
        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, invoiceId);

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

    /**
     * @param serviceId
     * @return
     */
    @Override
    public List<InvoiceHasService> selectByService(int serviceId) {
        String sql = "select * from invoicehasservice where serviceId = ?";
        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, serviceId);

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

    @Override
    public int update(InvoiceHasService obj) {
        String sql = "update invoicehasservice set quantity = ?, lineCost = ? where invoiceId = ? and serviceId = ?";
        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, obj.getQuantity());
            ps.setDouble(2, obj.getLineCost());
            ps.setInt(3, obj.getInvoiceId());
            ps.setInt(4, obj.getServiceId());

            int affectedRows = ps.executeUpdate();
            return affectedRows;
        }catch(SQLException e){
            e.printStackTrace();
        }

        return -1;
    }

    /**
     * @param rs
     * @return
     * @throws SQLException
     */
    public InvoiceHasService constructObject(ResultSet rs) throws SQLException {
        return new InvoiceHasService()
                .setInvoiceId(rs.getInt("invoiceId"))
                .setServiceId(rs.getInt("serviceId"))
                .setQuantity(rs.getInt("quantity"))
                .setLineCost(rs.getDouble("lineCost"));
    }
}
