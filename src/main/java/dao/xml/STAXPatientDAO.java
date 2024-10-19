package dao.xml;

import dao.PatientDAO;
import dao.factories.STAXDAOFactory;
import models.Patient;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class STAXPatientDAO implements PatientDAO {

    @Override
    public int insert(Patient obj) {
        throw new UnsupportedOperationException("STAXPatientDAO does not support insert operations.");
    }

    @Override
    public int update(int id, Patient obj) {
        throw new UnsupportedOperationException("STAXPatientDAO does not support update operations.");
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("STAXPatientDAO does not support delete operations.");
    }

    @Override
    public Optional<Patient> select(int id) {
        try (FileInputStream fis = new FileInputStream(STAXDAOFactory.filepath)) {
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(fis);
            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT) {
                    String elementName = reader.getLocalName();
                    if ("Patient".equals(elementName)) {
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

        try (FileInputStream fis = new FileInputStream(STAXDAOFactory.filepath)) {
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(fis);
            List<Patient> patients = new ArrayList<>();

            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT) {
                    String elementName = reader.getLocalName();
                    if ("Patient".equals(elementName)) {
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
                    if ("Patient".equals(endElementName)) {
                        return patient;
                    }
                    break;
            }
        }

        return null; // Return null if the element is not found
    }
}
