package dao.factories;

import dao.interfaces.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class DAOFactory {
    protected static Logger logger = LogManager.getLogger(DAOFactory.class);

    public static final int MYSQL = 1;
    public static final int MYBATIS = 2;
    public static final int STAX = 3;
    public static final int JAXB = 4;
    public static final int JACKSON = 5;

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
        DAOFactory.logger.trace("Entering getDAOFactory");
        DAOFactory factory =  switch(whichFactory){
            case MYSQL  -> new MySQLDAOFactory();
            case STAX   -> new StAXDAOFactory();
            case JAXB -> new JAXBDAOFactory();
            case JACKSON -> new JacksonDAOFactory();
            case MYBATIS -> new MyBatisDAOFactory();
            default -> null;
        };

        DAOFactory.logger.trace("Exiting getDAOFactory with factory {}", whichFactory);
        return factory;
    }

}
