package dao.mysql;

import dao.interfaces.InvoiceDAO;
import models.Invoice;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static dao.factories.MySQLDAOFactory.createConnection;

public class MySQLInvoiceDAO implements InvoiceDAO {
    /**
     * @param obj
     */
    @Override
    public int insert(Invoice obj) {
        String sql = "insert into invoice (total, isPaid, patientId, paymentDate) values(?, ?, ?, ?)";

        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, obj.getTotal());
            ps.setInt(2, obj.getIsPaid());
            ps.setInt(3, obj.getPatientId());
            if (obj.getPaymentDate() != null) {
                ps.setDate(4, Date.valueOf(obj.getPaymentDate()));
            } else {
                ps.setNull(4, java.sql.Types.DATE);
            }

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
    public int update(int id, Invoice obj) {
        String sql = "update invoice set " +
                "total = ?," +
                "isPaid = ?," +
                "patientId = ?," +
                "paymentDate = ?" +
                "where invoiceId = ?";

        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, obj.getTotal());
            ps.setInt(2, obj.getIsPaid());
            ps.setInt(3, obj.getPatientId());
            if (obj.getPaymentDate() != null) {
                ps.setDate(4, Date.valueOf(obj.getPaymentDate()));
            } else {
                ps.setNull(4, java.sql.Types.DATE);
            }
            ps.setInt(5, id);

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
        String sql = "delete from invoice where invoiceId = ?";
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
    public Optional<Invoice> select(int id) {
        String sql = "select * from invoice where invoiceId = ?";
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
    public List<Invoice> selectAll() {
        String sql = "select * from invoice";
        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            List<Invoice> list = new ArrayList<>();
            while(rs.next()){
                list.add(constructObject(rs));
            }
            return list;
        }catch(SQLException e){
            e.printStackTrace();
        }

        return List.of();
    }

    public Invoice constructObject(ResultSet rs) throws SQLException {
        Invoice invoice = new Invoice()
                .setInvoiceId(rs.getInt("invoiceId"))
                .setTotal(rs.getDouble("total"))
                .setIsPaid(rs.getInt("isPaid"))
                .setPatientId(rs.getInt("patientId"));

        Date paymentDate = rs.getDate("paymentDate");
        if (paymentDate != null) {
            invoice.setPaymentDate(paymentDate.toLocalDate());
        }

        return invoice;
    }
}
