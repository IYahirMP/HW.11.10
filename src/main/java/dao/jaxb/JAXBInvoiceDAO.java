package dao.jaxb;

import dao.interfaces.InvoiceDAO;
import dao.factories.JAXBDAOFactory;
import models.Invoice;
import models.xml.Hospital;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;
import java.util.Optional;

public class JAXBInvoiceDAO implements InvoiceDAO {
    private final String tableName = "Invoice";
    private final String xsdRoot = "src/main/resources/xsd/";
    private final String xsdFile = "Invoice.xsd";


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

    public Hospital getDB(){
        try {
            JAXBContext jaxbcontext = JAXBContext.newInstance(Hospital.class);
            Unmarshaller unmarshaller = jaxbcontext.createUnmarshaller();

            File file = new File(JAXBDAOFactory.filepath);
            return (Hospital) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }

}
