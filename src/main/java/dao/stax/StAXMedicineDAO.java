package dao.stax;

import dao.factories.StAXDAOFactory;
import dao.interfaces.MedicineDAO;
import models.Medicine;
import models.xml.Hospital;

import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StAXMedicineDAO extends StAXDAO implements MedicineDAO {
    private final String tableName = "Medicine";

    @Override
    public int insert(Medicine obj) {
        logger.debug("Retrieving DataBase");
        Hospital db = getDB();
        logger.trace("Database retrieved", db);
        logger.trace("Attempting to retrieve list of medicines.");
        List<Medicine> records = db.getMedicines();
        logger.debug("List of medicines retrieved");

        //It is important to check latest id
        logger.debug("Retrieving last register's id");
        int latestId = records.getLast().getMedicineId();
        logger.debug("Setting new object's id");
        obj.setMedicineId(latestId+1);

        logger.trace("Adding object to list");
        records.add(obj);
        logger.debug("Replacing database list of medicines");
        db.setMedicines(records);
        logger.trace("Entering WriteDB");
        writeDB(db);
        return logger.traceExit(1);
    }

    public int update(int id, Medicine obj) {
        logger.debug("Retrieving DataBase");
        Hospital db = getDB();
        logger.trace("Database retrieved", db);
        logger.trace("Attempting to retrieve list of medicines.");
        List<Medicine> records = db.getMedicines();
        logger.debug("List of medicines retrieved");;

        logger.debug("Searching for medicine with id: {}", id);
        if (obj != null) {
            records.forEach((p)-> {
                if (p.getMedicineId() == obj.getMedicineId()) {
                    logger.debug("Updating object's id");
                    records.set(records.indexOf(p), obj);
                }
            });

            db.setMedicines(records);
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
        logger.trace("Attempting to retrieve list of medicines.");
        List<Medicine> records = db.getMedicines();
        logger.debug("List of medicines retrieved");;

        logger.debug("Searching for medicine with id: {}", id);
        for (Medicine record : records) {
            if (record.getMedicineId() == id) {
                logger.debug("Deleting object's id");
                records.remove(record);
                db.setMedicines(records);
                logger.trace("Entering WriteDB");
                writeDB(db);
                return logger.traceExit(1);
            }
        }
        return 0;
    }

    @Override
    public Optional<Medicine> select(int id) {
        validateDatabase();

        try (FileInputStream fis = new FileInputStream(StAXDAOFactory.filepath)) {
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(fis);
            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT) {
                    String elementName = reader.getLocalName();
                    if (tableName.equals(elementName)) {
                        Medicine medicine = parseMedicine(reader);
                        if (medicine != null && medicine.getMedicineId() == id) {
                            return Optional.of(medicine);
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
    public List<Medicine> selectAll() {
        validateDatabase();

        try (FileInputStream fis = new FileInputStream(StAXDAOFactory.filepath)) {
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(fis);
            List<Medicine> medicines = new ArrayList<>();

            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT) {
                    String elementName = reader.getLocalName();
                    if (tableName.equals(elementName)) {
                        Medicine medicine = parseMedicine(reader);
                        if (medicine != null) {
                            medicines.add(medicine);
                        }
                    }
                }
            }

            return medicines;
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }

        return List.of();
    }

    private Medicine parseMedicine(XMLStreamReader reader) throws XMLStreamException {
        Medicine medicine = new Medicine();

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {
                case XMLStreamConstants.START_ELEMENT:
                    String elementName = reader.getLocalName();
                    if ("medicineId".equals(elementName)) {
                        medicine.setMedicineId(Integer.parseInt(reader.getElementText()));
                    } else if ("name".equals(elementName)) {
                        medicine.setName(reader.getElementText());
                    } else if ("cost".equals(elementName)) {
                        medicine.setCost(Double.parseDouble(reader.getElementText()));
                    } else if ("doseSize".equals(elementName)) {
                        medicine.setDoseSize(Integer.parseInt(reader.getElementText()));
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    String endElementName = reader.getLocalName();
                    if (tableName.equals(endElementName)) {
                        return medicine;
                    }
                    break;
            }
        }

        return null; // Return null if the element is not found
    }

    public void writeMedicine(XMLStreamWriter writer, Medicine medicine) throws XMLStreamException {
        writer.writeStartElement("Medicine");
        writer.writeStartElement("medicineId");
        writer.writeCharacters(String.valueOf(medicine.getMedicineId()));
        writer.writeEndElement();
        writer.writeStartElement("name");
        writer.writeCharacters(medicine.getName());
        writer.writeEndElement();
        writer.writeStartElement("cost");
        writer.writeCharacters(String.valueOf(medicine.getCost()));
        writer.writeEndElement();
        writer.writeStartElement("doseSize");
        writer.writeCharacters(String.valueOf(medicine.getDoseSize()));
        writer.writeEndElement();
        writer.writeEndElement();
    }

}

