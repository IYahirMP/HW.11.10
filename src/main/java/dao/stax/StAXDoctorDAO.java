package dao.stax;

import dao.factories.StAXDAOFactory;
import dao.interfaces.DoctorDAO;
import models.Doctor;
import models.xml.Hospital;

import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StAXDoctorDAO extends StAXDAO implements DoctorDAO {
    private final String tableName = "Doctor";
    private final String xsdRoot = "src/main/resources/xsd/";
    private final String xsdFile = "Doctor.xsd";


    @Override
    public int insert(Doctor obj) {
        logger.debug("Retrieving DataBase");
        Hospital db = getDB();
        logger.trace("Database retrieved", db);
        logger.trace("Attempting to retrieve list of doctors.");
        List<Doctor> records = db.getDoctors();
        logger.debug("List of doctors retrieved");

        //It is important to check latest id
        logger.debug("Retrieving last register's id");
        int latestId = records.getLast().getDoctorId();
        logger.debug("Setting new object's id");
        obj.setDoctorId(latestId+1);

        logger.trace("Adding object to list");
        records.add(obj);
        logger.debug("Replacing database list of doctors");
        db.setDoctors(records);
        logger.trace("Entering WriteDB");
        writeDB(db);
        return logger.traceExit(1);
    }

    public int update(int id, Doctor obj) {
        logger.debug("Retrieving DataBase");
        Hospital db = getDB();
        logger.trace("Database retrieved", db);
        logger.trace("Attempting to retrieve list of doctors.");
        List<Doctor> records = db.getDoctors();
        logger.debug("List of doctors retrieved");;

        logger.debug("Searching for doctor with id: {}", id);
        if (obj != null) {
            records.forEach((p)-> {
                if (p.getDoctorId() == obj.getDoctorId()) {
                    logger.debug("Updating object's id");
                    records.set(records.indexOf(p), obj);
                }
            });

            db.setDoctors(records);
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
        logger.trace("Attempting to retrieve list of doctors.");
        List<Doctor> records = db.getDoctors();
        logger.debug("List of doctors retrieved");;

        logger.debug("Searching for doctor with id: {}", id);
        for (Doctor record : records) {
            if (record.getDoctorId() == id) {
                logger.debug("Deleting object's id");
                records.remove(record);
                db.setDoctors(records);
                logger.trace("Entering WriteDB");
                writeDB(db);
                return logger.traceExit(1);
            }
        }
        return 0;
    }

    @Override
    public Optional<Doctor> select(int id) {
        validateDatabase();

        try (FileInputStream fis = new FileInputStream(StAXDAOFactory.filepath)) {
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(fis);
            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT) {
                    String elementName = reader.getLocalName();
                    if (tableName.equals(elementName)) {
                        Doctor doctor = parseDoctor(reader);
                        if (doctor != null && doctor.getDoctorId() == id) {
                            return Optional.of(doctor);
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
    public List<Doctor> selectAll() {
        validateDatabase();

        try (FileInputStream fis = new FileInputStream(StAXDAOFactory.filepath)) {
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(fis);
            List<Doctor> doctors = new ArrayList<>();

            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT) {
                    String elementName = reader.getLocalName();
                    if (tableName.equals(elementName)) {
                        Doctor doctor = parseDoctor(reader);
                        if (doctor != null) {
                            doctors.add(doctor);
                        }
                    }
                }
            }

            return doctors;
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }

        return List.of();
    }

    public void validate(){

    }

    private Doctor parseDoctor(XMLStreamReader reader) throws XMLStreamException {
        Doctor doctor = new Doctor();

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {
                case XMLStreamConstants.START_ELEMENT:
                    String elementName = reader.getLocalName();
                    if ("doctorId".equals(elementName)) {
                        doctor.setDoctorId(Integer.parseInt(reader.getElementText()));
                    } else if ("name".equals(elementName)) {
                        doctor.setName(reader.getElementText());
                    } else if ("phone".equals(elementName)) {
                        doctor.setPhone(reader.getElementText());
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    String endElementName = reader.getLocalName();
                    if (tableName.equals(endElementName)) {
                        return doctor;
                    }
                    break;
            }
        }

        return null; // Return null if the element is not found
    }

    public void writeDoctor(XMLStreamWriter writer, Doctor doctor) throws XMLStreamException {
        writer.writeStartElement("Doctor");
        writer.writeStartElement("doctorId");
        writer.writeCharacters(String.valueOf(doctor.getDoctorId()));
        writer.writeEndElement();
        writer.writeStartElement("name");
        writer.writeCharacters(doctor.getName());
        writer.writeEndElement();
        writer.writeStartElement("phone");
        writer.writeCharacters(doctor.getPhone());
        writer.writeEndElement();
        writer.writeEndElement();
    }


}
