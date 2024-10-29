package dao.stax;

import dao.factories.StAXDAOFactory;
import dao.interfaces.PrescriptionHasMedicineDAO;
import models.PrescriptionHasMedicine;
import models.xml.Hospital;

import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StAXPrescriptionHasMedicineDAO extends StAXDAO implements PrescriptionHasMedicineDAO {
    private final String tableName = "PrescriptionHasMedicine";

    @Override
    public int insert(PrescriptionHasMedicine obj) {
        logger.debug("Retrieving DataBase");
        Hospital db = getDB();
        logger.trace("Database retrieved", db);
        logger.trace("Attempting to retrieve list of prescriptionHasMedicines.");
        List<PrescriptionHasMedicine> records = db.getPrescriptionHasMedicines();
        logger.debug("List of prescriptionHasMedicines retrieved");

        //If there is no duplicate key:
        for (PrescriptionHasMedicine record : records) {
            if (record.getMedicineId() == obj.getMedicineId()
            && record.getPrescriptionId() == obj.getPrescriptionId()) {
                throw new RuntimeException("Element is already in the database. Cannot insert.");
            }
        }

        logger.trace("Adding object to list");
        records.add(obj);
        logger.debug("Replacing database list of prescriptionHasMedicines");
        db.setPrescriptionHasMedicines(records);
        logger.trace("Entering WriteDB");
        writeDB(db);
        return logger.traceExit(1);
    }

    public int update(PrescriptionHasMedicine obj) {
        logger.debug("Retrieving DataBase");
        Hospital db = getDB();
        logger.trace("Database retrieved", db);
        logger.trace("Attempting to retrieve list of prescriptionHasMedicines.");
        List<PrescriptionHasMedicine> records = db.getPrescriptionHasMedicines();
        logger.debug("List of prescriptionHasMedicines retrieved");;

        logger.debug("Searching for prescriptionHasMedicine with ids: {}, {}", obj.getMedicineId(), obj.getPrescriptionId());
        records.forEach((p) -> {
            if (p.getMedicineId() == obj.getMedicineId()
                    && p.getPrescriptionId() == obj.getPrescriptionId()) {
                logger.debug("Updating object's id");
                records.set(records.indexOf(p), obj);
            }
        });

        db.setPrescriptionHasMedicines(records);
        logger.trace("Entering WriteDB");
        writeDB(db);
        return logger.traceExit(1);
    }

    @Override
    public int delete(int prescriptionId, int medicineId) {
        logger.debug("Retrieving DataBase");
        Hospital db = getDB();
        logger.trace("Database retrieved", db);
        logger.trace("Attempting to retrieve list of prescriptionHasMedicines.");
        List<PrescriptionHasMedicine> records = db.getPrescriptionHasMedicines();
        logger.debug("List of prescriptionHasMedicines retrieved");;

        logger.debug("Searching for prescriptionHasMedicine with ids: {}, {}", prescriptionId, medicineId);
        for (PrescriptionHasMedicine record : records) {
            if (record.getMedicineId() == prescriptionId && record.getPrescriptionId() == medicineId) {
                logger.debug("Deleting object's id");
                records.remove(record);
                db.setPrescriptionHasMedicines(records);
                logger.trace("Entering WriteDB");
                writeDB(db);
                return logger.traceExit(1);
            }
        }
        return 0;
    }

    @Override
    public Optional<PrescriptionHasMedicine> select(int prescriptionId, int medicineId) {
        validateDatabase();

        try (FileInputStream fis = new FileInputStream(StAXDAOFactory.filepath)) {
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(fis);
            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT && "PrescriptionHasMedicine".equals(reader.getLocalName())) {
                    PrescriptionHasMedicine record = parsePrescriptionHasMedicine(reader);
                    if (record != null && record.getPrescriptionId() == prescriptionId && record.getMedicineId() == medicineId) {
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
    public List<PrescriptionHasMedicine> selectByPrescription(int prescriptionId) {
        validateDatabase();

        try (FileInputStream fis = new FileInputStream(StAXDAOFactory.filepath)) {
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(fis);
            List<PrescriptionHasMedicine> records = new ArrayList<>();

            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT && "PrescriptionHasMedicine".equals(reader.getLocalName())) {
                    PrescriptionHasMedicine record = parsePrescriptionHasMedicine(reader);
                    if (record != null && record.getPrescriptionId() == prescriptionId) {
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
    public List<PrescriptionHasMedicine> selectByMedicine(int medicineId) {
        validateDatabase();

        try (FileInputStream fis = new FileInputStream(StAXDAOFactory.filepath)) {
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(fis);
            List<PrescriptionHasMedicine> records = new ArrayList<>();

            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT && "PrescriptionHasMedicine".equals(reader.getLocalName())) {
                    PrescriptionHasMedicine record = parsePrescriptionHasMedicine(reader);
                    if (record != null && record.getMedicineId() == medicineId) {
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
    public List<PrescriptionHasMedicine> selectAll() {
        try (FileInputStream fis = new FileInputStream(StAXDAOFactory.filepath)) {
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(fis);
            List<PrescriptionHasMedicine> records = new ArrayList<>();

            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT && "PrescriptionHasMedicine".equals(reader.getLocalName())) {
                    PrescriptionHasMedicine record = parsePrescriptionHasMedicine(reader);
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

    private PrescriptionHasMedicine parsePrescriptionHasMedicine(XMLStreamReader reader) throws XMLStreamException {
        PrescriptionHasMedicine record = new PrescriptionHasMedicine();

        while (reader.hasNext()) {
            int event = reader.next();

            if (event == XMLStreamConstants.START_ELEMENT) {
                String elementName = reader.getLocalName();
                switch (elementName) {
                    case "prescriptionId":
                        record.setPrescriptionId(Integer.parseInt(reader.getElementText()));
                        break;
                    case "medicineId":
                        record.setMedicineId(Integer.parseInt(reader.getElementText()));
                        break;
                    case "prescribedTiming":
                        record.setPrescribedTiming(reader.getElementText());
                        break;
                    case "prescribedDose":
                        record.setPrescribedDose(reader.getElementText());
                        break;
                    case "prescribedDays":
                        record.setPrescribedDays(Integer.parseInt(reader.getElementText()));
                        break;
                }
            } else if (event == XMLStreamConstants.END_ELEMENT && reader.getLocalName().equals("PrescriptionHasMedicine")) {
                return record;
            }
        }

        return null; // Return null if the element is not found
    }

    public void writePrescriptionHasMedicine(XMLStreamWriter writer, PrescriptionHasMedicine prescriptionHasMedicine) throws XMLStreamException {
        writer.writeStartElement("PrescriptionHasMedicine");
        writer.writeStartElement("prescriptionId");
        writer.writeCharacters(String.valueOf(prescriptionHasMedicine.getPrescriptionId()));
        writer.writeEndElement();
        writer.writeStartElement("medicineId");
        writer.writeCharacters(String.valueOf(prescriptionHasMedicine.getMedicineId()));
        writer.writeEndElement();
        writer.writeStartElement("prescribedDays");
        writer.writeCharacters(String.valueOf(prescriptionHasMedicine.getPrescribedDays()));
        writer.writeEndElement();
        writer.writeStartElement("prescribedDose");
        writer.writeCharacters(prescriptionHasMedicine.getPrescribedDose());
        writer.writeEndElement();
        writer.writeStartElement("prescribedTiming");
        writer.writeCharacters(prescriptionHasMedicine.getPrescribedTiming());
        writer.writeEndElement();
        writer.writeEndElement();
    }


}
