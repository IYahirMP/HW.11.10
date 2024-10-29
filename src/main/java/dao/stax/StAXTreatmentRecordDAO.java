package dao.stax;

import dao.factories.StAXDAOFactory;
import dao.interfaces.TreatmentRecordDAO;
import models.TreatmentRecord;
import models.xml.Hospital;
import models.xml.LocalDateTimeAdapter;

import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StAXTreatmentRecordDAO extends StAXDAO implements TreatmentRecordDAO {
    private final String tableName = "TreatmentRecord";
    private final String xsdRoot = "src/main/resources/xsd/";
    private final String xsdFile = "TreatmentRecord.xsd";

    @Override
    public int insert(TreatmentRecord obj) {
        logger.debug("Retrieving DataBase");
        Hospital db = getDB();
        logger.trace("Database retrieved", db);
        logger.trace("Attempting to retrieve list of treatmentRecords.");
        List<TreatmentRecord> records = db.getTreatmentRecords();
        logger.debug("List of treatmentRecords retrieved");

        //It is important to check latest id
        logger.debug("Retrieving last register's id");
        int latestId = records.getLast().getRecordId();
        logger.debug("Setting new object's id");
        obj.setRecordId(latestId+1);

        logger.trace("Adding object to list");
        records.add(obj);
        logger.debug("Replacing database list of treatmentRecords");
        db.setTreatmentRecords(records);
        logger.trace("Entering WriteDB");
        writeDB(db);
        return logger.traceExit(1);
    }

    public int update(int id, TreatmentRecord obj) {
        logger.debug("Retrieving DataBase");
        Hospital db = getDB();
        logger.trace("Database retrieved", db);
        logger.trace("Attempting to retrieve list of treatmentRecords.");
        List<TreatmentRecord> records = db.getTreatmentRecords();
        logger.debug("List of treatmentRecords retrieved");;

        logger.debug("Searching for treatmentRecord with id: {}", id);
        if (obj != null) {
            records.forEach((p)-> {
                if (p.getRecordId() == obj.getRecordId()) {
                    logger.debug("Updating object's id");
                    records.set(records.indexOf(p), obj);
                }
            });

            db.setTreatmentRecords(records);
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
        logger.trace("Attempting to retrieve list of treatmentRecords.");
        List<TreatmentRecord> records = db.getTreatmentRecords();
        logger.debug("List of treatmentRecords retrieved");;

        logger.debug("Searching for treatmentRecord with id: {}", id);
        for (TreatmentRecord record : records) {
            if (record.getRecordId() == id) {
                logger.debug("Deleting object's id");
                records.remove(record);
                db.setTreatmentRecords(records);
                logger.trace("Entering WriteDB");
                writeDB(db);
                return logger.traceExit(1);
            }
        }
        return 0;
    }

    @Override
    public Optional<TreatmentRecord> select(int id) {
        validateDatabase();

        try (FileInputStream fis = new FileInputStream(StAXDAOFactory.filepath)) {
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(fis);
            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT) {
                    String elementName = reader.getLocalName();
                    if (tableName.equals(elementName)) {
                        TreatmentRecord record = parseTreatmentRecord(reader);
                        if (record != null && record.getRecordId() == id) {
                            return Optional.of(record);
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
    public List<TreatmentRecord> selectAll() {
        validateDatabase();

        try (FileInputStream fis = new FileInputStream(StAXDAOFactory.filepath)) {
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(fis);
            List<TreatmentRecord> records = new ArrayList<>();

            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT) {
                    String elementName = reader.getLocalName();
                    if (tableName.equals(elementName)) {
                        TreatmentRecord record = parseTreatmentRecord(reader);
                        if (record != null) {
                            records.add(record);
                        }
                    }
                }
            }

            return records;
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }

        return List.of();
    }

    private TreatmentRecord parseTreatmentRecord(XMLStreamReader reader) throws XMLStreamException {
        TreatmentRecord record = new TreatmentRecord();

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {
                case XMLStreamConstants.START_ELEMENT:
                    String elementName = reader.getLocalName();
                    if ("recordId".equals(elementName)) {
                        record.setRecordId(Integer.parseInt(reader.getElementText()));
                    } else if ("admissionId".equals(elementName)) {
                        record.setAdmissionId(Integer.parseInt(reader.getElementText()));
                    } else if ("notes".equals(elementName)) {
                        record.setNotes(reader.getElementText());
                    } else if ("time".equals(elementName)) {
                        try{
                            LocalDateTime time = new LocalDateTimeAdapter().unmarshal(reader.getElementText());
                            record.setTime(time);
                        }catch(Exception e){
                            throw new RuntimeException("Error parsing time: " + reader.getElementText());
                        }
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    String endElementName = reader.getLocalName();
                    if (tableName.equals(endElementName)) {
                        return record;
                    }
                    break;
            }
        }

        return null; // Return null if the element is not found
    }

    public void writeTreatmentRecord(XMLStreamWriter writer, TreatmentRecord treatmentRecord) throws XMLStreamException {
        writer.writeStartElement("TreatmentRecord");
        writer.writeStartElement("recordId");
        writer.writeCharacters(String.valueOf(treatmentRecord.getRecordId()));
        writer.writeEndElement();
        writer.writeStartElement("admissionId");
        writer.writeCharacters(String.valueOf(treatmentRecord.getAdmissionId()));
        writer.writeEndElement();
        writer.writeStartElement("time");
        writer.writeCharacters(String.valueOf(treatmentRecord.getTime()));
        writer.writeEndElement();
        writer.writeStartElement("notes");
        writer.writeCharacters(treatmentRecord.getNotes());
        writer.writeEndElement();
        writer.writeEndElement();
    }


}
