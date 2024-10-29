package dao.stax;

import dao.factories.StAXDAOFactory;
import dao.interfaces.PrescriptionDAO;
import models.Prescription;
import models.xml.Hospital;

import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StAXPrescriptionDAO extends StAXDAO implements PrescriptionDAO {
    private final String tableName = "Prescription";
    private final String xsdRoot = "src/main/resources/xsd/";
    private final String xsdFile = "Prescription.xsd";

    @Override
    public int insert(Prescription obj) {
        logger.debug("Retrieving DataBase");
        Hospital db = getDB();
        logger.trace("Database retrieved", db);
        logger.trace("Attempting to retrieve list of prescriptions.");
        List<Prescription> records = db.getPrescriptions();
        logger.debug("List of prescriptions retrieved");

        //It is important to check latest id
        logger.debug("Retrieving last register's id");
        int latestId = records.getLast().getPrescriptionId();
        logger.debug("Setting new object's id");
        obj.setPrescriptionId(latestId+1);

        logger.trace("Adding object to list");
        records.add(obj);
        logger.debug("Replacing database list of prescriptions");
        db.setPrescriptions(records);
        logger.trace("Entering WriteDB");
        writeDB(db);
        return logger.traceExit(1);
    }

    public int update(int id, Prescription obj) {
        logger.debug("Retrieving DataBase");
        Hospital db = getDB();
        logger.trace("Database retrieved", db);
        logger.trace("Attempting to retrieve list of prescriptions.");
        List<Prescription> records = db.getPrescriptions();
        logger.debug("List of prescriptions retrieved");;

        logger.debug("Searching for prescription with id: {}", id);
        if (obj != null) {
            records.forEach((p)-> {
                if (p.getPrescriptionId() == obj.getPrescriptionId()) {
                    logger.debug("Updating object's id");
                    records.set(records.indexOf(p), obj);
                }
            });

            db.setPrescriptions(records);
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
        logger.trace("Attempting to retrieve list of prescriptions.");
        List<Prescription> records = db.getPrescriptions();
        logger.debug("List of prescriptions retrieved");;

        logger.debug("Searching for prescription with id: {}", id);
        for (Prescription record : records) {
            if (record.getPrescriptionId() == id) {
                logger.debug("Deleting object's id");
                records.remove(record);
                db.setPrescriptions(records);
                logger.trace("Entering WriteDB");
                writeDB(db);
                return logger.traceExit(1);
            }
        }
        return 0;
    }

    @Override
    public Optional<Prescription> select(int id) {
        validateDatabase();

        try (FileInputStream fis = new FileInputStream(StAXDAOFactory.filepath)) {
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(fis);
            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT) {
                    String elementName = reader.getLocalName();
                    if (tableName.equals(elementName)) {
                        Prescription prescription = parsePrescription(reader);
                        if (prescription != null && prescription.getPrescriptionId() == id) {
                            return Optional.of(prescription);
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
    public List<Prescription> selectAll() {
        validateDatabase();

        try (FileInputStream fis = new FileInputStream(StAXDAOFactory.filepath)) {
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(fis);
            List<Prescription> prescriptions = new ArrayList<>();

            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT) {
                    String elementName = reader.getLocalName();
                    if (tableName.equals(elementName)) {
                        Prescription prescription = parsePrescription(reader);
                        if (prescription != null) {
                            prescriptions.add(prescription);
                        }
                    }
                }
            }

            return prescriptions;
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }

        return List.of();
    }

    private Prescription parsePrescription(XMLStreamReader reader) throws XMLStreamException {
        Prescription prescription = new Prescription();

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {
                case XMLStreamConstants.START_ELEMENT:
                    String elementName = reader.getLocalName();
                    if ("prescriptionId".equals(elementName)) {
                        prescription.setPrescriptionId(Integer.parseInt(reader.getElementText()));
                    } else if ("patientId".equals(elementName)) {
                        prescription.setPatientId(Integer.parseInt(reader.getElementText()));
                    } else if ("diagnose".equals(elementName)) {
                        prescription.setDiagnose(reader.getElementText());
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    String endElementName = reader.getLocalName();
                    if (tableName.equals(endElementName)) {
                        return prescription;
                    }
                    break;
            }
        }

        return null; // Return null if the element is not found
    }

    public void writePrescription(XMLStreamWriter writer, Prescription prescription) throws XMLStreamException {
        writer.writeStartElement("Prescription");
        writer.writeStartElement("prescriptionId");
        writer.writeCharacters(String.valueOf(prescription.getPrescriptionId()));
        writer.writeEndElement();
        writer.writeStartElement("patientId");
        writer.writeCharacters(String.valueOf(prescription.getPatientId()));
        writer.writeEndElement();
        writer.writeStartElement("diagnose");
        writer.writeCharacters(prescription.getDiagnose());
        writer.writeEndElement();
        writer.writeEndElement();
    }


}
