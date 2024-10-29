package dao.stax;

import dao.factories.StAXDAOFactory;
import dao.interfaces.AdmissionRecordDAO;
import models.AdmissionRecord;
import models.xml.Hospital;

import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StAXAdmissionRecordDAO extends StAXDAO implements AdmissionRecordDAO {
    private final String tableName = "AdmissionRecord";
    private final String xsdRoot = "src/main/resources/xsd/";
    private final String xsdFile = "AdmissionRecord.xsd";


    @Override
    public int insert(AdmissionRecord obj) {
        logger.debug("Retrieving DataBase");
        Hospital db = getDB();
        logger.trace("Database retrieved", db);
        logger.trace("Attempting to retrieve list of admissionRecords.");
        List<AdmissionRecord> records = db.getAdmissionRecords();
        logger.debug("List of admissionRecords retrieved");

        //It is important to check latest id
        logger.debug("Retrieving last register's id");
        int latestId = records.getLast().getAdmissionId();
        logger.debug("Setting new object's id");
        obj.setAdmissionId(latestId+1);

        logger.trace("Adding object to list");
        records.add(obj);
        logger.debug("Replacing database list of admissionRecords");
        db.setAdmissionRecords(records);
        logger.trace("Entering WriteDB");
        writeDB(db);
        return logger.traceExit(1);
    }

    public int update(int id, AdmissionRecord obj) {
        logger.debug("Retrieving DataBase");
        Hospital db = getDB();
        logger.trace("Database retrieved", db);
        logger.trace("Attempting to retrieve list of admissionRecords.");
        List<AdmissionRecord> records = db.getAdmissionRecords();
        logger.debug("List of admissionRecords retrieved");;

        logger.debug("Searching for admissionRecord with id: {}", id);
        if (obj != null) {
            records.forEach((p)-> {
                if (p.getAdmissionId() == obj.getAdmissionId()) {
                    logger.debug("Updating object's id");
                    records.set(records.indexOf(p), obj);
                }
            });

            db.setAdmissionRecords(records);
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
        logger.trace("Attempting to retrieve list of admissionRecords.");
        List<AdmissionRecord> records = db.getAdmissionRecords();
        logger.debug("List of admissionRecords retrieved");;

        logger.debug("Searching for admissionRecord with id: {}", id);
        for (AdmissionRecord record : records) {
            if (record.getAdmissionId() == id) {
                logger.debug("Deleting object's id");
                records.remove(record);
                db.setAdmissionRecords(records);
                logger.trace("Entering WriteDB");
                writeDB(db);
                return logger.traceExit(1);
            }
        }
        return 0;
    }

    @Override
    public Optional<AdmissionRecord> select(int id) {
        validateDatabase();

        try (FileInputStream fis = new FileInputStream(StAXDAOFactory.filepath)) {
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(fis);
            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT) {
                    String elementName = reader.getLocalName();
                    if (tableName.equals(elementName)) {
                        AdmissionRecord admissionRecord = parseAdmissionRecord(reader);
                        if (admissionRecord != null && admissionRecord.getAdmissionId() == id) {
                            return Optional.of(admissionRecord);
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
    public List<AdmissionRecord> selectAll() {
        validateDatabase();

        try (FileInputStream fis = new FileInputStream(StAXDAOFactory.filepath)) {
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(fis);
            List<AdmissionRecord> admissionRecords = new ArrayList<>();

            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT) {
                    String elementName = reader.getLocalName();
                    if (tableName.equals(elementName)) {
                        AdmissionRecord admissionRecord = parseAdmissionRecord(reader);
                        if (admissionRecord != null) {
                            admissionRecords.add(admissionRecord);
                        }
                    }
                }
            }

            return admissionRecords;
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }

        return List.of();
    }

    public void validate(){

    }

    private AdmissionRecord parseAdmissionRecord(XMLStreamReader reader) throws XMLStreamException {
        AdmissionRecord admissionRecord = new AdmissionRecord();

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {
                case XMLStreamConstants.START_ELEMENT:
                    String elementName = reader.getLocalName();
                    if ("admissionId".equals(elementName)) {
                        admissionRecord.setAdmissionId(Integer.parseInt(reader.getElementText()));
                    } else if ("patientId".equals(elementName)) {
                        admissionRecord.setPatientId(Integer.parseInt(reader.getElementText()));
                    } else if ("consultationId".equals(elementName)) {
                        admissionRecord.setConsultationId(Integer.parseInt(reader.getElementText()));
                    } else if ("admissionDate".equals(elementName)) {
                        admissionRecord.setAdmissionDate(LocalDate.parse(reader.getElementText()));
                    } else if ("dischargeDate".equals(elementName)) {
                        admissionRecord.setDischargeDate(LocalDate.parse(reader.getElementText()));
                    } else if ("roomNumber".equals(elementName)) {
                        admissionRecord.setRoomNumber(Integer.parseInt(reader.getElementText()));
                    } else if ("bedNumber".equals(elementName)) {
                        admissionRecord.setBedNumber(Integer.parseInt(reader.getElementText()));
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    String endElementName = reader.getLocalName();
                    if (tableName.equals(endElementName)) {
                        return admissionRecord;
                    }
                    break;
            }
        }

        return null; // Return null if the element is not found
    }

    public void writeAdmissionRecord(XMLStreamWriter writer, AdmissionRecord admissionRecord) throws XMLStreamException {
        writer.writeStartElement("AdmissionRecord");
        writer.writeStartElement("admissionId");
        writer.writeCharacters(String.valueOf(admissionRecord.getAdmissionId()));
        writer.writeEndElement();
        writer.writeStartElement("patientId");
        writer.writeCharacters(String.valueOf(admissionRecord.getPatientId()));
        writer.writeEndElement();
        writer.writeStartElement("consultationId");
        writer.writeCharacters(String.valueOf(admissionRecord.getConsultationId()));
        writer.writeEndElement();
        writer.writeStartElement("admissionDate");
        writer.writeCharacters(String.valueOf(admissionRecord.getAdmissionDate()));
        writer.writeEndElement();
        writer.writeStartElement("dischargeDate");
        writer.writeCharacters(String.valueOf(admissionRecord.getDischargeDate()));
        writer.writeEndElement();
        writer.writeStartElement("roomNumber");
        writer.writeCharacters(String.valueOf(admissionRecord.getRoomNumber()));
        writer.writeEndElement();
        writer.writeStartElement("bedNumber");
        writer.writeCharacters(String.valueOf(admissionRecord.getBedNumber()));
        writer.writeEndElement();
        writer.writeEndElement();
    }

}
