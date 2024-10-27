package dao.jackson;

import dao.interfaces.InvoiceDAO;
import models.Invoice;

import java.util.List;
import java.util.Optional;

public class JacksonInvoiceDAO extends JacksonWrapper implements InvoiceDAO {

    @Override
    public int insert(Invoice obj) {
        throw new UnsupportedOperationException("STAXInvoiceDAO does not support insert operations.");
    }

    @Override
    public int update(int id, Invoice obj) {
        throw new UnsupportedOperationException("STAXInvoiceDAO does not support update operations.");
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("STAXInvoiceDAO does not support delete operations.");
    }

    @Override
    public Optional<Invoice> select(int id) {
        return getDB().getInvoices().stream().filter((i) -> i.getInvoiceId() == id).findFirst();
    }

    @Override
    public List<Invoice> selectAll() {
        return getDB().getInvoices();
    }

}
