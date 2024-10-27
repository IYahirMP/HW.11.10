package dao.mysql;

import dao.interfaces.InvoiceHasServiceDAO;
import models.InvoiceHasService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MySQLInvoiceHasServiceDAO implements InvoiceHasServiceDAO {


    public InvoiceHasService constructObject(ResultSet rs) throws SQLException {
        return new InvoiceHasService()
                .setLineCost(rs.getDouble("LineCost"))
                .setInvoiceId(rs.getInt("InvoiceId"))
                .setServiceId(rs.getInt("ServiceId"))
                .setQuantity(rs.getInt("Quantity"));
    }

    /**
     * @param obj
     * @return
     */
    @Override
    public int update(InvoiceHasService obj) {
        return 0;
    }

    /**
     * @param obj
     * @return
     */
    @Override
    public int insert(InvoiceHasService obj) {
        return 0;
    }

    /**
     * @param invoiceId
     * @param serviceId
     * @return
     */
    @Override
    public int delete(int invoiceId, int serviceId) {
        return 0;
    }

    /**
     * @param invoiceId
     * @param serviceId
     * @return
     */
    @Override
    public Optional<InvoiceHasService> select(int invoiceId, int serviceId) {
        return Optional.empty();
    }

    /**
     * @return
     */
    @Override
    public List<InvoiceHasService> selectAll() {
        return List.of();
    }

    /**
     * @param invoiceId
     * @return
     */
    @Override
    public List<InvoiceHasService> selectByInvoice(int invoiceId) {
        return List.of();
    }

    /**
     * @param serviceId
     * @return
     */
    @Override
    public List<InvoiceHasService> selectByService(int serviceId) {
        return List.of();
    }
}
