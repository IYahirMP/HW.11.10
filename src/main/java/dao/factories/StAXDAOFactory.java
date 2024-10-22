package dao.factories;

import dao.*;
import dao.stax.*;

public class StAXDAOFactory extends DAOFactory{

    public static String filepath = "";

    /**
     * @return
     */
    @Override
    public ConsultationDAO getConsultationDAO() {
        return new StAXConsultationDAO();
    }

    /**
     * @return
     */
    @Override
    public PatientDAO getPatientDAO() {
        return new StAXPatientDAO();
    }

    /**
     * @return
     */
    @Override
    public DoctorDAO getDoctorDAO() {
        return new StAXDoctorDAO();
    }

    /**
     * @return
     */
    @Override
    public AdmissionRecordDAO getAdmissionRecordDAO() {
        return new StAXAdmissionRecordDAO();
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
        return new StAXInvoiceDAO();
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
