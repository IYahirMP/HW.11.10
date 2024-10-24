package dao.jaxb;

import dao.interfaces.AdmissionRecordDAO;
import dao.factories.JAXBDAOFactory;
import models.AdmissionRecord;
import models.xml.Hospital;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;
import java.util.Optional;

public class JAXBAdmissionRecordDAO implements AdmissionRecordDAO {
    private final String tableName = "AdmissionRecord";
    private final String xsdRoot = "src/main/resources/xsd/";
    private final String xsdFile = "AdmissionRecord.xsd";


    @Override
    public int insert(AdmissionRecord obj) {
        throw new UnsupportedOperationException("STAXAdmissionRecordDAO does not support insert operations.");
    }

    @Override
    public int update(int id, AdmissionRecord obj) {
        throw new UnsupportedOperationException("STAXAdmissionRecordDAO does not support update operations.");
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("STAXAdmissionRecordDAO does not support delete operations.");
    }

    @Override
    public Optional<AdmissionRecord> select(int id) {
        return getDB().getAdmissionRecords().stream().filter((a) -> a.getAdmissionId() == id).findFirst();
    }

    @Override
    public List<AdmissionRecord> selectAll() {
        return getDB().getAdmissionRecords();
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
