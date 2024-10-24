package dao.stax;

import dao.interfaces.ConsultationDAO;
import dao.factories.StAXDAOFactory;
import models.Consultation;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StAXConsultationDAO implements ConsultationDAO {
    private final String tableName = "Consultation";
    private final String xsdRoot = "src/main/resources/xsd/";
    private final String xsdFile = "Consultation.xsd";


    @Override
    public int insert(Consultation obj) {
        throw new UnsupportedOperationException("STAXConsultationDAO does not support insert operations.");
    }

    @Override
    public int update(int id, Consultation obj) {
        throw new UnsupportedOperationException("STAXConsultationDAO does not support update operations.");
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("STAXConsultationDAO does not support delete operations.");
    }

    @Override
    public Optional<Consultation> select(int id) {
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
}
