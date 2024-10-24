package dao.jaxb;

import dao.ConsultationDAO;
import dao.factories.JAXBDAOFactory;
import dao.factories.StAXDAOFactory;
import models.Consultation;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JAXBConsultationDAO implements ConsultationDAO {
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
        return getDB().getConsultations().stream().filter((c) -> c.getConsultationId() == id).findFirst();
    }

    @Override
    public List<Consultation> selectAll() {
        return getDB().getConsultations();
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
