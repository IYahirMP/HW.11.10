package dao.stax;

import dao.factories.StAXDAOFactory;
import dao.interfaces.EmergencyContactDAO;
import models.EmergencyContact;
import models.xml.Hospital;

import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StAXEmergencyContactDAO extends StAXDAO implements EmergencyContactDAO {
    private final String tableName = "EmergencyContact";
    private final String xsdRoot = "src/main/resources/xsd/";
    private final String xsdFile = "EmergencyContact.xsd";

    @Override
    public int insert(EmergencyContact obj) {
        logger.debug("Retrieving DataBase");
        Hospital db = getDB();
        logger.trace("Database retrieved", db);
        logger.trace("Attempting to retrieve list of emergencyContacts.");
        List<EmergencyContact> records = db.getEmergencyContacts();
        logger.debug("List of emergencyContacts retrieved");

        //It is important to check latest id
        logger.debug("Retrieving last register's id");
        int latestId = records.getLast().getEmergencyContactId();
        logger.debug("Setting new object's id");
        obj.setEmergencyContactId(latestId+1);

        logger.trace("Adding object to list");
        records.add(obj);
        logger.debug("Replacing database list of emergencyContacts");
        db.setEmergencyContacts(records);
        logger.trace("Entering WriteDB");
        writeDB(db);
        return logger.traceExit(1);
    }

    public int update(int id, EmergencyContact obj) {
        logger.debug("Retrieving DataBase");
        Hospital db = getDB();
        logger.trace("Database retrieved", db);
        logger.trace("Attempting to retrieve list of emergencyContacts.");
        List<EmergencyContact> records = db.getEmergencyContacts();
        logger.debug("List of emergencyContacts retrieved");;

        logger.debug("Searching for emergencyContact with id: {}", id);
        if (obj != null) {
            records.forEach((p)-> {
                if (p.getEmergencyContactId() == obj.getEmergencyContactId()) {
                    logger.debug("Updating object's id");
                    records.set(records.indexOf(p), obj);
                }
            });

            db.setEmergencyContacts(records);
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
        logger.trace("Attempting to retrieve list of emergencyContacts.");
        List<EmergencyContact> records = db.getEmergencyContacts();
        logger.debug("List of emergencyContacts retrieved");;

        logger.debug("Searching for emergencyContact with id: {}", id);
        for (EmergencyContact record : records) {
            if (record.getEmergencyContactId() == id) {
                logger.debug("Deleting object's id");
                records.remove(record);
                db.setEmergencyContacts(records);
                logger.trace("Entering WriteDB");
                writeDB(db);
                return logger.traceExit(1);
            }
        }
        return 0;
    }

    @Override
    public Optional<EmergencyContact> select(int id) {
        validateDatabase();

        try (FileInputStream fis = new FileInputStream(StAXDAOFactory.filepath)) {
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(fis);
            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT) {
                    String elementName = reader.getLocalName();
                    if (tableName.equals(elementName)) {
                        EmergencyContact emergencyContact = parseEmergencyContact(reader);
                        if (emergencyContact != null && emergencyContact.getEmergencyContactId() == id) {
                            return Optional.of(emergencyContact);
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
    public List<EmergencyContact> selectAll() {
        validateDatabase();

        try (FileInputStream fis = new FileInputStream(StAXDAOFactory.filepath)) {
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(fis);
            List<EmergencyContact> emergencyContacts = new ArrayList<>();

            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT) {
                    String elementName = reader.getLocalName();
                    if (tableName.equals(elementName)) {
                        EmergencyContact emergencyContact = parseEmergencyContact(reader);
                        if (emergencyContact != null) {
                            emergencyContacts.add(emergencyContact);
                        }
                    }
                }
            }

            return emergencyContacts;
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }

        return List.of();
    }

    private EmergencyContact parseEmergencyContact(XMLStreamReader reader) throws XMLStreamException {
        EmergencyContact emergencyContact = new EmergencyContact();

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {
                case XMLStreamConstants.START_ELEMENT:
                    String elementName = reader.getLocalName();
                    if ("emergencyContactId".equals(elementName)) {
                        emergencyContact.setEmergencyContactId(Integer.parseInt(reader.getElementText()));
                    } else if ("name".equals(elementName)) {
                        emergencyContact.setName(reader.getElementText());
                    } else if ("phone".equals(elementName)) {
                        emergencyContact.setPhone(reader.getElementText());
                    } else if ("address".equals(elementName)) {
                        emergencyContact.setAddress(reader.getElementText());
                    } else if ("patientId".equals(elementName)) {
                        emergencyContact.setPatientId(Integer.parseInt(reader.getElementText()));
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    String endElementName = reader.getLocalName();
                    if (tableName.equals(endElementName)) {
                        return emergencyContact;
                    }
                    break;
            }
        }

        return null; // Return null if the element is not found
    }

    public void writeEmergencyContact(XMLStreamWriter writer, EmergencyContact emergencyContact) throws XMLStreamException {
        writer.writeStartElement("EmergencyContact");
        writer.writeStartElement("emergencyContactId");
        writer.writeCharacters(String.valueOf(emergencyContact.getEmergencyContactId()));
        writer.writeEndElement();
        writer.writeStartElement("name");
        writer.writeCharacters(emergencyContact.getName());
        writer.writeEndElement();
        writer.writeStartElement("phone");
        writer.writeCharacters(emergencyContact.getPhone());
        writer.writeEndElement();
        writer.writeStartElement("address");
        writer.writeCharacters(emergencyContact.getAddress());
        writer.writeEndElement();
        writer.writeStartElement("patientId");
        writer.writeCharacters(String.valueOf(emergencyContact.getPatientId()));
        writer.writeEndElement();
        writer.writeEndElement();
    }


}
