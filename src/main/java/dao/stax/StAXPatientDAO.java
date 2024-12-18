package dao.stax;

import dao.factories.StAXDAOFactory;
import dao.interfaces.PatientDAO;
import models.Patient;
import models.xml.Hospital;

import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StAXPatientDAO extends StAXDAO implements PatientDAO {
    private final String tableName = "Patient";


    @Override
    public int insert(Patient obj) {
        logger.debug("Retrieving DataBase");
        Hospital db = getDB();
        logger.trace("Database retrieved", db);
        logger.trace("Attempting to retrieve list of patients.");
        List<Patient> records = db.getPatients();
        logger.debug("List of patients retrieved");

        //It is important to check latest id
        logger.debug("Retrieving last register's id");
        int latestId = records.getLast().getPatientId();
        logger.debug("Setting new object's id");
        obj.setPatientId(latestId+1);

        logger.trace("Adding object to list");
        records.add(obj);
        logger.debug("Replacing database list of patients");
        db.setPatients(records);
        logger.trace("Entering WriteDB");
        writeDB(db);
        return logger.traceExit(1);
    }

    public int update(int id, Patient obj) {
        logger.debug("Retrieving DataBase");
        Hospital db = getDB();
        logger.trace("Database retrieved", db);
        logger.trace("Attempting to retrieve list of patients.");
        List<Patient> records = db.getPatients();
        logger.debug("List of patients retrieved");;

        logger.debug("Searching for patient with id: {}", id);
        if (obj != null) {
            records.forEach((p)-> {
                if (p.getPatientId() == obj.getPatientId()) {
                    logger.debug("Updating object's id");
                    records.set(records.indexOf(p), obj);
                }
            });

            db.setPatients(records);
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
        logger.trace("Attempting to retrieve list of patients.");
        List<Patient> records = db.getPatients();
        logger.debug("List of patients retrieved");;

        logger.debug("Searching for patient with id: {}", id);
        for (Patient record : records) {
            if (record.getPatientId() == id) {
                logger.debug("Deleting object's id");
                records.remove(record);
                db.setPatients(records);
                logger.trace("Entering WriteDB");
                writeDB(db);
                return logger.traceExit(1);
            }
        }
        return 0;
    }

    @Override
    public Optional<Patient> select(int id) {
        validateDatabase();

        try (FileInputStream fis = new FileInputStream(StAXDAOFactory.filepath)) {
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(fis);
            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT) {
                    String elementName = reader.getLocalName();
                    if (tableName.equals(elementName)) {
                        Patient patient = parsePatient(reader);
                        if (patient != null && patient.getPatientId() == id) {
                            return Optional.of(patient);
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
    public List<Patient> selectAll() {
        validateDatabase();

        try (FileInputStream fis = new FileInputStream(StAXDAOFactory.filepath)) {
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(fis);
            List<Patient> patients = new ArrayList<>();

            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT) {
                    String elementName = reader.getLocalName();
                    if (tableName.equals(elementName)) {
                        Patient patient = parsePatient(reader);
                        if (patient != null) {
                            patients.add(patient);
                        }
                    }
                }
            }

            return patients;
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }

        return List.of();
    }

    public void validate(){

    }

    private Patient parsePatient(XMLStreamReader reader) throws XMLStreamException {
        Patient patient = new Patient();

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {
                case XMLStreamConstants.START_ELEMENT:
                    String elementName = reader.getLocalName();
                    if ("patientId".equals(elementName)) {
                        patient.setPatientId(Integer.parseInt(reader.getElementText()));
                    } else if ("name".equals(elementName)) {
                        patient.setName(reader.getElementText());
                    } else if ("age".equals(elementName)) {
                        patient.setAge(Integer.parseInt(reader.getElementText()));
                    } else if ("address".equals(elementName)) {
                        patient.setAddress(reader.getElementText());
                    } else if ("phone".equals(elementName)) {
                        patient.setPhone(reader.getElementText());
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    String endElementName = reader.getLocalName();
                    if (tableName.equals(endElementName)) {
                        return patient;
                    }
                    break;
            }
        }

        return null; // Return null if the element is not found
    }

    public void writePatient(XMLStreamWriter writer, Patient patient) throws XMLStreamException {
        writer.writeStartElement("Patient");
        writer.writeStartElement("patientId");
        writer.writeCharacters(String.valueOf(patient.getPatientId()));
        writer.writeEndElement();
        writer.writeStartElement("name");
        writer.writeCharacters(patient.getName());
        writer.writeEndElement();
        writer.writeStartElement("age");
        writer.writeCharacters(String.valueOf(patient.getAge()));
        writer.writeEndElement();
        writer.writeStartElement("address");
        writer.writeCharacters(patient.getAddress());
        writer.writeEndElement();
        writer.writeStartElement("phone");
        writer.writeCharacters(patient.getPhone());
        writer.writeEndElement();
        writer.writeEndElement();
    }
}
