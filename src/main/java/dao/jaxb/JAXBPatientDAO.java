package dao.jaxb;

import dao.interfaces.PatientDAO;
import dao.factories.JAXBDAOFactory;
import models.Patient;
import models.xml.Hospital;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;
import java.util.Optional;

public class JAXBPatientDAO implements PatientDAO {
    private final String tableName = "Patient";
    private final String xsdRoot = "src/main/resources/xsd/";
    private final String xsdFile = "Patient.xsd";


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
        return getDB().getPatients().stream().filter((p) -> p.getPatientId()==id).findFirst();
    }

    @Override
    public List<Patient> selectAll() {
        return getDB().getPatients();
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
