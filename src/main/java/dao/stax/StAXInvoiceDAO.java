package dao.stax;

import dao.factories.StAXDAOFactory;
import dao.interfaces.InvoiceDAO;
import models.Invoice;
import models.xml.Hospital;

import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StAXInvoiceDAO extends StAXDAO implements InvoiceDAO {
    private final String tableName = "Invoice";
    private final String xsdRoot = "src/main/resources/xsd/";
    private final String xsdFile = "Invoice.xsd";


    @Override
    public int insert(Invoice obj) {
        logger.debug("Retrieving DataBase");
        Hospital db = getDB();
        logger.trace("Database retrieved", db);
        logger.trace("Attempting to retrieve list of invoices.");
        List<Invoice> records = db.getInvoices();
        logger.debug("List of invoices retrieved");

        //It is important to check latest id
        logger.debug("Retrieving last register's id");
        int latestId = records.getLast().getInvoiceId();
        logger.debug("Setting new object's id");
        obj.setInvoiceId(latestId+1);

        logger.trace("Adding object to list");
        records.add(obj);
        logger.debug("Replacing database list of invoices");
        db.setInvoices(records);
        logger.trace("Entering WriteDB");
        writeDB(db);
        return logger.traceExit(1);
    }

    public int update(int id, Invoice obj) {
        logger.debug("Retrieving DataBase");
        Hospital db = getDB();
        logger.trace("Database retrieved", db);
        logger.trace("Attempting to retrieve list of invoices.");
        List<Invoice> records = db.getInvoices();
        logger.debug("List of invoices retrieved");;

        logger.debug("Searching for invoice with id: {}", id);
        if (obj != null) {
            records.forEach((p)-> {
                if (p.getInvoiceId() == obj.getInvoiceId()) {
                    logger.debug("Updating object's id");
                    records.set(records.indexOf(p), obj);
                }
            });

            db.setInvoices(records);
            logger.trace("Entering WriteDB");
            writeDB(db);
            return logger.traceExit(1);
        }
        return logger.traceExit(0);    }

    @Override
    public int delete(int id) {
        logger.debug("Retrieving DataBase");
        Hospital db = getDB();
        logger.trace("Database retrieved", db);
        logger.trace("Attempting to retrieve list of invoices.");
        List<Invoice> records = db.getInvoices();
        logger.debug("List of invoices retrieved");;

        logger.debug("Searching for invoice with id: {}", id);
        for (Invoice record : records) {
            if (record.getInvoiceId() == id) {
                logger.debug("Deleting object's id");
                records.remove(record);
                db.setInvoices(records);
                logger.trace("Entering WriteDB");
                writeDB(db);
                return logger.traceExit(1);
            }
        }
        return 0;
    }

    @Override
    public Optional<Invoice> select(int id) {
        validateDatabase();

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
        validateDatabase();

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

    public void writeInvoice(XMLStreamWriter writer, Invoice invoice) throws XMLStreamException {
        writer.writeStartElement("Invoice");
        writer.writeStartElement("invoiceId");
        writer.writeCharacters(String.valueOf(invoice.getInvoiceId()));
        writer.writeEndElement();
        writer.writeStartElement("total");
        writer.writeCharacters(String.valueOf(invoice.getTotal()));
        writer.writeEndElement();
        writer.writeStartElement("isPaid");
        writer.writeCharacters(String.valueOf(invoice.getIsPaid()));
        writer.writeEndElement();
        writer.writeStartElement("patientId");
        writer.writeCharacters(String.valueOf(invoice.getPatientId()));
        writer.writeEndElement();
        writer.writeStartElement("paymentDate");
        writer.writeCharacters(String.valueOf(invoice.getPaymentDate()));
        writer.writeEndElement();
        writer.writeEndElement();
    }

}
