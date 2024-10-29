package dao.stax;

import dao.factories.StAXDAOFactory;
import dao.interfaces.ConsultationDAO;
import models.Consultation;
import models.xml.Hospital;

import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StAXConsultationDAO extends StAXDAO implements ConsultationDAO {
    private final String tableName = "Consultation";
    private final String xsdRoot = "src/main/resources/xsd/";
    private final String xsdFile = "Consultation.xsd";


    @Override
    public int insert(Consultation obj) {
        logger.debug("Retrieving DataBase");
        Hospital db = getDB();
        logger.trace("Database retrieved", db);
        logger.trace("Attempting to retrieve list of consultations.");
        List<Consultation> records = db.getConsultations();
        logger.debug("List of consultations retrieved");

        //It is important to check latest id
        logger.debug("Retrieving last register's id");
        int latestId = records.getLast().getConsultationId();
        logger.debug("Setting new object's id");
        obj.setConsultationId(latestId+1);

        logger.trace("Adding object to list");
        records.add(obj);
        logger.debug("Replacing database list of consultations");
        db.setConsultations(records);
        logger.trace("Entering WriteDB");
        writeDB(db);
        return logger.traceExit(1);
    }

    public int update(int id, Consultation obj) {
        logger.debug("Retrieving DataBase");
        Hospital db = getDB();
        logger.trace("Database retrieved", db);
        logger.trace("Attempting to retrieve list of consultations.");
        List<Consultation> records = db.getConsultations();
        logger.debug("List of consultations retrieved");;

        logger.debug("Searching for consultation with id: {}", id);
        if (obj != null) {
            records.forEach((p)-> {
                if (p.getConsultationId() == obj.getConsultationId()) {
                    logger.debug("Updating object's id");
                    records.set(records.indexOf(p), obj);
                }
            });

            db.setConsultations(records);
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
        logger.trace("Attempting to retrieve list of consultations.");
        List<Consultation> records = db.getConsultations();
        logger.debug("List of consultations retrieved");;

        logger.debug("Searching for consultation with id: {}", id);
        for (Consultation record : records) {
            if (record.getConsultationId() == id) {
                logger.debug("Deleting object's id");
                records.remove(record);
                db.setConsultations(records);
                logger.trace("Entering WriteDB");
                writeDB(db);
                return logger.traceExit(1);
            }
        }
        return 0;
    }

    @Override
    public Optional<Consultation> select(int id) {
        validateDatabase();

        try (FileInputStream fis = new FileInputStream(StAXDAOFactory.filepath)) {
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(fis);
            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT) {
                    String elementName = reader.getLocalName();
                    if (tableName.equals(elementName)) {
                        Consultation consultation = parseConsultation(reader);
                        if (consultation != null && consultation.getConsultationId() == id) {
                            return Optional.of(consultation);
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
    public List<Consultation> selectAll() {
        validateDatabase();

        try (FileInputStream fis = new FileInputStream(StAXDAOFactory.filepath)) {
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(fis);
            List<Consultation> consultations = new ArrayList<>();

            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT) {
                    String elementName = reader.getLocalName();
                    if (tableName.equals(elementName)) {
                        Consultation consultation = parseConsultation(reader);
                        if (consultation != null) {
                            consultations.add(consultation);
                        }
                    }
                }
            }

            return consultations;
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }

        return List.of();
    }

    public void validate(){

    }

    private Consultation parseConsultation(XMLStreamReader reader) throws XMLStreamException {
        Consultation consultation = new Consultation();

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {
                case XMLStreamConstants.START_ELEMENT:
                    String elementName = reader.getLocalName();
                    if ("consultationId".equals(elementName)) {
                        consultation.setConsultationId(Integer.parseInt(reader.getElementText()));
                    } else if ("date".equals(elementName)) {
                        consultation.setDate(LocalDate.parse(reader.getElementText()));
                    } else if ("doctorId".equals(elementName)) {
                        consultation.setDoctorId(Integer.parseInt(reader.getElementText()));
                    } else if ("patientId".equals(elementName)) {
                        consultation.setPatientId(Integer.parseInt(reader.getElementText()));
                    } else if ("diagnose".equals(elementName)) {
                        consultation.setDiagnose(reader.getElementText());
                    } else if ("prescriptionId".equals(elementName)) {
                        consultation.setPrescriptionId(Integer.parseInt(reader.getElementText()));
                    } else if ("admittedForTreatment".equals(elementName)) {
                        consultation.setAdmittedForTreatment(Integer.parseInt(reader.getElementText()));
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    String endElementName = reader.getLocalName();
                    if (tableName.equals(endElementName)) {
                        return consultation;
                    }
                    break;
            }
        }

        return null; // Return null if the element is not found
    }

    public void writeConsultation(XMLStreamWriter writer, Consultation consultation) throws XMLStreamException {
        writer.writeStartElement("Consultation");
        writer.writeStartElement("consultationId");
        writer.writeCharacters(String.valueOf(consultation.getConsultationId()));
        writer.writeEndElement();
        writer.writeStartElement("date");
        writer.writeCharacters(String.valueOf(consultation.getDate()));
        writer.writeEndElement();
        writer.writeStartElement("doctorId");
        writer.writeCharacters(String.valueOf(consultation.getDoctorId()));
        writer.writeEndElement();
        writer.writeStartElement("patientId");
        writer.writeCharacters(String.valueOf(consultation.getPatientId()));
        writer.writeEndElement();
        writer.writeStartElement("diagnose");
        writer.writeCharacters(consultation.getDiagnose());
        writer.writeEndElement();
        writer.writeStartElement("prescriptionId");
        writer.writeCharacters(String.valueOf(consultation.getPrescriptionId()));
        writer.writeEndElement();
        writer.writeStartElement("admittedForTreatment");
        writer.writeCharacters(String.valueOf(consultation.getAdmittedForTreatment()));
        writer.writeEndElement();
        writer.writeEndElement();
    }

}
