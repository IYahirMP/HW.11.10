package dao.stax;

import dao.factories.StAXDAOFactory;
import dao.interfaces.InvoiceHasServiceDAO;
import models.InvoiceHasService;
import models.xml.Hospital;

import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StAXInvoiceHasServiceDAO extends StAXDAO implements InvoiceHasServiceDAO {
    private final String tableName = "InvoiceHasService";

    @Override
    public int insert(InvoiceHasService obj) {
        logger.debug("Retrieving DataBase");
        Hospital db = getDB();
        logger.trace("Database retrieved", db);
        logger.trace("Attempting to retrieve list of invoiceHasServices.");
        List<InvoiceHasService> records = db.getInvoiceHasServices();
        logger.debug("List of invoiceHasServices retrieved");

        //If there is no duplicate key:
        for (InvoiceHasService record : records) {
            if (record.getInvoiceId() == obj.getInvoiceId()
                    && record.getServiceId() == obj.getServiceId()) {
                throw new RuntimeException("Element is already in the database. Cannot insert.");
            }
        }

        logger.trace("Adding object to list");
        records.add(obj);
        logger.debug("Replacing database list of invoiceHasServices");
        db.setInvoiceHasServices(records);
        logger.trace("Entering WriteDB");
        writeDB(db);
        return logger.traceExit(1);
    }

    public int update(InvoiceHasService obj) {
        logger.debug("Retrieving DataBase");
        Hospital db = getDB();
        logger.trace("Database retrieved", db);
        logger.trace("Attempting to retrieve list of invoiceHasServices.");
        List<InvoiceHasService> records = db.getInvoiceHasServices();
        logger.debug("List of invoiceHasServices retrieved");;

        logger.debug("Searching for invoiceHasService with ids: {}, {}", obj.getInvoiceId(), obj.getServiceId());
        records.forEach((p) -> {
            if (p.getServiceId() == obj.getServiceId()
                    && p.getInvoiceId() == obj.getInvoiceId()) {
                logger.debug("Updating object's id");
                records.set(records.indexOf(p), obj);
            }
        });

        db.setInvoiceHasServices(records);
        logger.trace("Entering WriteDB");
        writeDB(db);
        return logger.traceExit(1);
    }

    @Override
    public int delete(int invoiceId, int serviceId) {
        logger.debug("Retrieving DataBase");
        Hospital db = getDB();
        logger.trace("Database retrieved", db);
        logger.trace("Attempting to retrieve list of invoiceHasServices.");
        List<InvoiceHasService> records = db.getInvoiceHasServices();
        logger.debug("List of invoiceHasServices retrieved");;

        logger.debug("Searching for invoiceHasService with ids: {}, {}", invoiceId, serviceId);
        for (InvoiceHasService record : records) {
            if (record.getInvoiceId() == invoiceId && record.getServiceId() == serviceId) {
                logger.debug("Deleting object's id");
                records.remove(record);
                db.setInvoiceHasServices(records);
                logger.trace("Entering WriteDB");
                writeDB(db);
                return logger.traceExit(1);
            }
        }
        return 0;
    }

    @Override
    public Optional<InvoiceHasService> select(int invoiceId, int serviceId) {
        validateDatabase();

        try (FileInputStream fis = new FileInputStream(StAXDAOFactory.filepath)) {
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(fis);
            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT && "InvoiceHasService".equals(reader.getLocalName())) {
                    InvoiceHasService record = parseInvoiceHasService(reader);
                    if (record != null && record.getInvoiceId() == invoiceId && record.getServiceId() == serviceId) {
                        return Optional.of(record);
                    }
                }
            }
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<InvoiceHasService> selectByInvoice(int invoiceId) {
        validateDatabase();

        try (FileInputStream fis = new FileInputStream(StAXDAOFactory.filepath)) {
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(fis);
            List<InvoiceHasService> records = new ArrayList<>();

            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT && "InvoiceHasService".equals(reader.getLocalName())) {
                    InvoiceHasService record = parseInvoiceHasService(reader);
                    if (record != null && record.getInvoiceId() == invoiceId) {
                        records.add(record);
                    }
                }
            }

            return records;
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }

        return List.of();
    }

    @Override
    public List<InvoiceHasService> selectAll() {
        validateDatabase();

        try (FileInputStream fis = new FileInputStream(StAXDAOFactory.filepath)) {
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(fis);
            List<InvoiceHasService> records = new ArrayList<>();

            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT && "InvoiceHasService".equals(reader.getLocalName())) {
                    InvoiceHasService record = parseInvoiceHasService(reader);
                    if (record != null) {
                        records.add(record);
                    }
                }
            }

            return records;
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }

        return List.of();
    }

    @Override
    public List<InvoiceHasService> selectByService(int serviceId) {
        validateDatabase();

        try (FileInputStream fis = new FileInputStream(StAXDAOFactory.filepath)) {
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(fis);
            List<InvoiceHasService> records = new ArrayList<>();

            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT && "InvoiceHasService".equals(reader.getLocalName())) {
                    InvoiceHasService record = parseInvoiceHasService(reader);
                    if (record != null && record.getServiceId() == serviceId) {
                        records.add(record);
                    }
                }
            }

            return records;
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }

        return List.of();
    }

    private InvoiceHasService parseInvoiceHasService(XMLStreamReader reader) throws XMLStreamException {
        InvoiceHasService record = new InvoiceHasService();

        while (reader.hasNext()) {
            int event = reader.next();

            if (event == XMLStreamConstants.START_ELEMENT) {
                String elementName = reader.getLocalName();
                switch (elementName) {
                    case "invoiceId":
                        record.setInvoiceId(Integer.parseInt(reader.getElementText()));
                        break;
                    case "serviceId":
                        record.setServiceId(Integer.parseInt(reader.getElementText()));
                        break;
                    case "quantity":
                        record.setQuantity(Integer.parseInt(reader.getElementText()));
                        break;
                    case "lineCost":
                        record.setLineCost(Double.parseDouble(reader.getElementText()));
                        break;
                }
            } else if (event == XMLStreamConstants.END_ELEMENT && reader.getLocalName().equals("InvoiceHasService")) {
                return record;
            }
        }

        return null; // Return null if the element is not found
    }

    public void writeInvoiceHasService(XMLStreamWriter writer, InvoiceHasService invoiceHasService) throws XMLStreamException {
        writer.writeStartElement("InvoiceHasService");
        writer.writeStartElement("invoiceId");
        writer.writeCharacters(String.valueOf(invoiceHasService.getInvoiceId()));
        writer.writeEndElement();
        writer.writeStartElement("serviceId");
        writer.writeCharacters(String.valueOf(invoiceHasService.getServiceId()));
        writer.writeEndElement();
        writer.writeStartElement("quantity");
        writer.writeCharacters(String.valueOf(invoiceHasService.getQuantity()));
        writer.writeEndElement();
        writer.writeStartElement("lineCost");
        writer.writeCharacters(String.valueOf(invoiceHasService.getLineCost()));
        writer.writeEndElement();
        writer.writeEndElement();
    }


}
