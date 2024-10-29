package dao.stax;

import dao.factories.StAXDAOFactory;
import dao.interfaces.ServiceDAO;
import models.Service;
import models.xml.Hospital;

import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StAXServiceDAO extends StAXDAO implements ServiceDAO {
    private final String tableName = "Service";
    private final String xsdRoot = "src/main/resources/xsd/";
    private final String xsdFile = "Service.xsd";

    @Override
    public int insert(Service obj) {
        logger.debug("Retrieving DataBase");
        Hospital db = getDB();
        logger.trace("Database retrieved", db);
        logger.trace("Attempting to retrieve list of services.");
        List<Service> records = db.getServices();
        logger.debug("List of services retrieved");

        //It is important to check latest id
        logger.debug("Retrieving last register's id");
        int latestId = records.getLast().getServiceId();
        logger.debug("Setting new object's id");
        obj.setServiceId(latestId+1);

        logger.trace("Adding object to list");
        records.add(obj);
        logger.debug("Replacing database list of services");
        db.setServices(records);
        logger.trace("Entering WriteDB");
        writeDB(db);
        return logger.traceExit(1);
    }

    public int update(int id, Service obj) {
        logger.debug("Retrieving DataBase");
        Hospital db = getDB();
        logger.trace("Database retrieved", db);
        logger.trace("Attempting to retrieve list of services.");
        List<Service> records = db.getServices();
        logger.debug("List of services retrieved");;

        logger.debug("Searching for service with id: {}", id);
        if (obj != null) {
            records.forEach((p)-> {
                if (p.getServiceId() == obj.getServiceId()) {
                    logger.debug("Updating object's id");
                    records.set(records.indexOf(p), obj);
                }
            });

            db.setServices(records);
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
        logger.trace("Attempting to retrieve list of services.");
        List<Service> records = db.getServices();
        logger.debug("List of services retrieved");;

        logger.debug("Searching for service with id: {}", id);
        for (Service record : records) {
            if (record.getServiceId() == id) {
                logger.debug("Deleting object's id");
                records.remove(record);
                db.setServices(records);
                logger.trace("Entering WriteDB");
                writeDB(db);
                return logger.traceExit(1);
            }
        }
        return 0;
    }

    @Override
    public Optional<Service> select(int id) {
        validateDatabase();

        try (FileInputStream fis = new FileInputStream(StAXDAOFactory.filepath)) {
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(fis);
            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT) {
                    String elementName = reader.getLocalName();
                    if (tableName.equals(elementName)) {
                        Service service = parseService(reader);
                        if (service != null && service.getServiceId() == id) {
                            return Optional.of(service);
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
    public List<Service> selectAll() {
        validateDatabase();

        try (FileInputStream fis = new FileInputStream(StAXDAOFactory.filepath)) {
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(fis);
            List<Service> services = new ArrayList<>();

            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT) {
                    String elementName = reader.getLocalName();
                    if (tableName.equals(elementName)) {
                        Service service = parseService(reader);
                        if (service != null) {
                            services.add(service);
                        }
                    }
                }
            }

            return services;
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }

        return List.of();
    }

    private Service parseService(XMLStreamReader reader) throws XMLStreamException {
        Service service = new Service();

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {
                case XMLStreamConstants.START_ELEMENT:
                    String elementName = reader.getLocalName();
                    if ("serviceId".equals(elementName)) {
                        service.setServiceId(Integer.parseInt(reader.getElementText()));
                    } else if ("description".equals(elementName)) {
                        service.setDescription(reader.getElementText());
                    } else if ("cost".equals(elementName)) {
                        service.setCost(Double.parseDouble(reader.getElementText()));
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    String endElementName = reader.getLocalName();
                    if (tableName.equals(endElementName)) {
                        return service;
                    }
                    break;
            }
        }

        return null; // Return null if the element is not found
    }

    public void writeService(XMLStreamWriter writer, Service service) throws XMLStreamException {
        writer.writeStartElement("Service");
        writer.writeStartElement("serviceId");
        writer.writeCharacters(String.valueOf(service.getServiceId()));
        writer.writeEndElement();
        writer.writeStartElement("description");
        writer.writeCharacters(service.getDescription());
        writer.writeEndElement();
        writer.writeStartElement("cost");
        writer.writeCharacters(String.valueOf(service.getCost()));
        writer.writeEndElement();
        writer.writeEndElement();
    }


}
