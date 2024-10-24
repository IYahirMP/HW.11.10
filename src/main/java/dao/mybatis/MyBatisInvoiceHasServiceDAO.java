package dao.mybatis;

import dao.interfaces.InvoiceHasServiceDAO;
import models.InvoiceHasService;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static dao.factories.MyBatisDAOFactory.createConnection;

public class MyBatisInvoiceHasServiceDAO implements InvoiceHasServiceDAO {

    /**
     * @param obj
     * @return
     */
    @Override
    public int update(InvoiceHasService obj) {
        try (SqlSession con = createConnection()) {
            InvoiceHasServiceDAO mapper = con.getMapper(InvoiceHasServiceDAO.class);
            int rowsAffected = mapper.update(obj);
con.commit();
return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * @param obj
     * @return
     */
    @Override
    public int insert(InvoiceHasService obj) {
        try (SqlSession con = createConnection()) {
            InvoiceHasServiceDAO mapper = con.getMapper(InvoiceHasServiceDAO.class);
            int rowsAffected = mapper.insert(obj);
con.commit();
return rowsAffected;
        } catch (SQLException e) {
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
        try (SqlSession con = createConnection()) {
            InvoiceHasServiceDAO mapper = con.getMapper(InvoiceHasServiceDAO.class);
            int deletedRows = mapper.delete(invoiceId, serviceId);
            con.commit();
            return deletedRows;
        } catch (SQLException e) {
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
        try (SqlSession con = createConnection()) {
            InvoiceHasServiceDAO mapper = con.getMapper(InvoiceHasServiceDAO.class);
            return mapper.select(invoiceId, serviceId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * @return
     */
    @Override
    public List<InvoiceHasService> selectAll() {
        try (SqlSession con = createConnection()) {
            InvoiceHasServiceDAO mapper = con.getMapper(InvoiceHasServiceDAO.class);
            return mapper.selectAll();
        } catch (SQLException e) {
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
        try (SqlSession con = createConnection()) {
            InvoiceHasServiceDAO mapper = con.getMapper(InvoiceHasServiceDAO.class);
            return mapper.selectByInvoice(invoiceId);
        } catch (SQLException e) {
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
        try (SqlSession con = createConnection()) {
            InvoiceHasServiceDAO mapper = con.getMapper(InvoiceHasServiceDAO.class);
            return mapper.selectByService(serviceId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }
}
