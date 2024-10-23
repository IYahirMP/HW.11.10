package dao.factories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dao.*;
import dao.jackson.*;
import dao.jaxb.*;
import models.xml.Hospital;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class JacksonDAOFactory extends DAOFactory{

    public static String filepath = "";

    /**
     * @return
     */
    @Override
    public ConsultationDAO getConsultationDAO() {
        return new JacksonConsultationDAO();
    }

    /**
     * @return
     */
    @Override
    public PatientDAO getPatientDAO() {
        return new JacksonPatientDAO();
    }

    /**
     * @return
     */
    @Override
    public DoctorDAO getDoctorDAO() {
        return new JacksonDoctorDAO();
    }

    /**
     * @return
     */
    @Override
    public AdmissionRecordDAO getAdmissionRecordDAO() {
        return new JacksonAdmissionRecordDAO();
    }

    /**
     * @return
     */
    @Override
    public EmergencyContactDAO getEmergencyContactDAO() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public InvoiceDAO getInvoiceDAO() {
        return new JacksonInvoiceDAO();
    }

    /**
     * @return
     */
    @Override
    public InvoiceHasServiceDAO getInvoiceHasServiceDAO() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public MedicineDAO getMedicineDAO() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public PrescriptionDAO getPrescriptionDAO() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public PrescriptionHasMedicineDAO getPrescriptionHasMedicineDAO() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public ServiceDAO getServiceDAO() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public TreatmentRecordDAO getTreatmentRecordDAO() {
        return null;
    }

    public static Hospital getDB(){
        try {
            File file = new File(JacksonDAOFactory.filepath);
            FileInputStream fis = new FileInputStream(file);
            String jsonDB = new String(fis.readAllBytes());

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
            return objectMapper.readValue(jsonDB, Hospital.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
