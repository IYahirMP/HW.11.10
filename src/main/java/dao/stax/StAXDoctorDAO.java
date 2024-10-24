package dao.stax;

import dao.interfaces.DoctorDAO;
import dao.factories.StAXDAOFactory;
import models.Doctor;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StAXDoctorDAO implements DoctorDAO {
    private final String tableName = "Doctor";
    private final String xsdRoot = "src/main/resources/xsd/";
    private final String xsdFile = "Doctor.xsd";


    @Override
    public int insert(Doctor obj) {
        throw new UnsupportedOperationException("STAXDoctorDAO does not support insert operations.");
    }

    @Override
    public int update(int id, Doctor obj) {
        throw new UnsupportedOperationException("STAXDoctorDAO does not support update operations.");
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("STAXDoctorDAO does not support delete operations.");
    }

    @Override
    public Optional<Doctor> select(int id) {
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
}
