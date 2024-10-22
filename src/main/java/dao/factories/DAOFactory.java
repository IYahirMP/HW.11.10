package dao.factories;

import dao.*;

public abstract class DAOFactory {
    public static final int MYSQL = 1;
    public static final int STAX = 2;

    public abstract ConsultationDAO getConsultationDAO();
    public abstract PatientDAO getPatientDAO();
    public abstract DoctorDAO getDoctorDAO();
    public abstract AdmissionRecordDAO getAdmissionRecordDAO();
    public abstract EmergencyContactDAO getEmergencyContactDAO();
    public abstract InvoiceDAO getInvoiceDAO();
    public abstract InvoiceHasServiceDAO getInvoiceHasServiceDAO();
    public abstract MedicineDAO getMedicineDAO();
    public abstract PrescriptionDAO getPrescriptionDAO();
    public abstract PrescriptionHasMedicineDAO getPrescriptionHasMedicineDAO();
    public abstract ServiceDAO getServiceDAO();
    public abstract TreatmentRecordDAO getTreatmentRecordDAO();

    public static DAOFactory getDAOFactory(int whichFactory){
        DAOFactory factory = switch(whichFactory){
            case MYSQL  -> new MySQLDAOFactory();
            case STAX   -> new StAXDAOFactory();
            default -> null;
        };

        return factory;
    }

}
