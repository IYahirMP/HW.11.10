package dao.interfaces;

import models.InvoiceHasService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface InvoiceHasServiceDAO extends JointTableCrud<InvoiceHasService> {
    int update(InvoiceHasService obj);
    int insert(InvoiceHasService obj);
    int delete(int invoiceId, int serviceId);
    Optional<InvoiceHasService> select(int invoiceId, int serviceId);
    List<InvoiceHasService> selectByInvoice(int invoiceId);
    List<InvoiceHasService> selectByService(int serviceId);

}
