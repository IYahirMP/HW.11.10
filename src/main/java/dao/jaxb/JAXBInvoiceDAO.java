package dao.jaxb;

import dao.interfaces.InvoiceDAO;
import models.Invoice;
import models.xml.Hospital;

import java.util.List;
import java.util.Optional;

public class JAXBInvoiceDAO extends JAXBWrapper implements InvoiceDAO {

    @Override
    public int insert(Invoice obj) {
	logger.trace("AccessingDataBase");
        Hospital db = getDB();
        List<Invoice> records = db.getInvoices();

        //It is important to check latest id
        int latestId = records.getLast().getInvoiceId();
        obj.setInvoiceId(latestId+1);

        if (obj != null) {
            records.add(obj);
            db.setInvoices(records);
            writeDB(db);
            return 1;
        }
        return 0;
    }

    @Override
    public int update(int id, Invoice obj) {
        Hospital db = getDB();
        List<Invoice> records = db.getInvoices();

        if (obj != null) {
            records.forEach((p)-> {
                if (p.getInvoiceId() == obj.getInvoiceId()) {
                    records.set(records.indexOf(p), obj);
                }
            });
            db.setInvoices(records);
            writeDB(db);
            return 1;
        }
        return 0;    }

    @Override
    public int delete(int id) {
        Hospital db = getDB();
        List<Invoice> records = db.getInvoices();
        for (Invoice record : records) {
            if (record.getInvoiceId() == id) {
                records.remove(record);
                db.setInvoices(records);
                writeDB(db);
                return 1;
            }
        }
        return 0;
    }

    @Override
    public Optional<Invoice> select(int id) {
        return getDB().getInvoices().stream().filter((a) -> a.getInvoiceId() == id).findFirst();
    }

    @Override
    public List<Invoice> selectAll() {
        return getDB().getInvoices();
    }

}
