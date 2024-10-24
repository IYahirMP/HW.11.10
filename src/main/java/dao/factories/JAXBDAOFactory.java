package dao.factories;

import dao.interfaces.*;
import dao.jaxb.*;

public class JAXBDAOFactory extends DAOFactory{

    public static String filepath = "";

    /**
     * @return
     */
    @Override
    public ConsultationDAO getConsultationDAO() {
        return new JAXBConsultationDAO();
    }

    /**
     * @return
     */
    @Override
    public PatientDAO getPatientDAO() {
        return new JAXBPatientDAO();
    }

    /**
     * @return
     */
    @Override
    public DoctorDAO getDoctorDAO() {
        return new JAXBDoctorDAO();
    }

    /**
     * @return
     */
    @Override
    public AdmissionRecordDAO getAdmissionRecordDAO() {
        return new JAXBAdmissionRecordDAO();
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
        return new JAXBInvoiceDAO();
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
}
