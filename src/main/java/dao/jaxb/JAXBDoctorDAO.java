package dao.jaxb;

import dao.DoctorDAO;
import dao.factories.JAXBDAOFactory;
import dao.factories.StAXDAOFactory;
import models.Doctor;
import models.Patient;
import models.xml.Hospital;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JAXBDoctorDAO implements DoctorDAO {
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
        return getDB().getDoctors().stream().filter((d) -> d.getDoctorId() == id).findFirst();
    }

    @Override
    public List<Doctor> selectAll() {
        return getDB().getDoctors();
    }

    public Hospital getDB(){
        try {
            JAXBContext jaxbcontext = JAXBContext.newInstance(Hospital.class);
            Unmarshaller unmarshaller = jaxbcontext.createUnmarshaller();

            File file = new File(JAXBDAOFactory.filepath);
            return (Hospital) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }
}
