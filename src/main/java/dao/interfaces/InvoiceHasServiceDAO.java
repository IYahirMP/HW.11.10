package dao.interfaces;

import models.InvoiceHasService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface InvoiceHasServiceDAO extends JointTableCrud<InvoiceHasService> {
    int update(InvoiceHasService obj);
    int insert(InvoiceHasService obj);
    int delete(@Param("invoiceId") int invoiceId, @Param("serviceId") int serviceId);
    Optional<InvoiceHasService> select(@Param("invoiceId") int invoiceId, @Param("serviceId") int serviceId);
    List<InvoiceHasService> selectByInvoice(int invoiceId);
    List<InvoiceHasService> selectByService(int serviceId);

}
