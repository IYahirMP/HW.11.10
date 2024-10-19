package dao.factories;

import dao.*;
import dao.xml.STAXPatientDAO;

public class STAXDAOFactory extends DAOFactory{

    public static String filepath = "";

    /**
     * @return
     */
    @Override
    public ConsultationDAO getConsultationDAO() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public PatientDAO getPatientDAO() {
        return new STAXPatientDAO();
    }

    /**
     * @return
     */
    @Override
    public DoctorDAO getDoctorDAO() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public AdmissionRecordDAO getAdmissionRecordDAO() {
        return null;
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
        return null;
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
