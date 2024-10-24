package dao.stax;

import dao.interfaces.InvoiceDAO;
import dao.factories.StAXDAOFactory;
import models.Invoice;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StAXInvoiceDAO implements InvoiceDAO {
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
        try (FileInputStream fis = new FileInputStream(StAXDAOFactory.filepath)) {
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(fis);
            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT) {
                    String elementName = reader.getLocalName();
                    if (tableName.equals(elementName)) {
                        Invoice invoice = parseInvoice(reader);
                        if (invoice != null && invoice.getInvoiceId() == id) {
                            return Optional.of(invoice);
                        }
                    }
                }
            }
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<Invoice> selectAll() {

        try (FileInputStream fis = new FileInputStream(StAXDAOFactory.filepath)) {
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(fis);
            List<Invoice> invoices = new ArrayList<>();

            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT) {
                    String elementName = reader.getLocalName();
                    if (tableName.equals(elementName)) {
                        Invoice invoice = parseInvoice(reader);
                        if (invoice != null) {
                            invoices.add(invoice);
                        }
                    }
                }
            }

            return invoices;
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }

        return List.of();
    }

    public void validate(){

    }

    private Invoice parseInvoice(XMLStreamReader reader) throws XMLStreamException {
        Invoice invoice = new Invoice();

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {
                case XMLStreamConstants.START_ELEMENT:
                    String elementName = reader.getLocalName();
                    if ("invoiceId".equals(elementName)) {
                        invoice.setInvoiceId(Integer.parseInt(reader.getElementText()));
                    } else if ("total".equals(elementName)) {
                        invoice.setTotal(Double.parseDouble(reader.getElementText()));
                    } else if ("isPaid".equals(elementName)) {
                        invoice.setIsPaid(Integer.parseInt(reader.getElementText()));
                    } else if ("patientId".equals(elementName)) {
                        invoice.setPatientId(Integer.parseInt(reader.getElementText()));
                    } else if ("paymentDate".equals(elementName)) {
                        invoice.setPaymentDate(LocalDate.parse(reader.getElementText()));
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    String endElementName = reader.getLocalName();
                    if (tableName.equals(endElementName)) {
                        return invoice;
                    }
                    break;
            }
        }

        return null; // Return null if the element is not found
    }
}
