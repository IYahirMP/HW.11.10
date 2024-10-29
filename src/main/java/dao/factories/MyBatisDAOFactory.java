package dao.factories;

import dao.interfaces.*;
import dao.mybatis.proxy.*;
import dao.mybatis.proxy.MyBatisInvoiceHasServiceDAOProxy;
import dao.mybatis.proxy.MyBatisPrescriptionHasMedicineDAOProxy;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.SQLException;

public class MyBatisDAOFactory extends DAOFactory{
    public static SqlSessionFactory sessionFactory;

    public static SqlSession createConnection() throws SQLException{
            return sessionFactory.openSession();
    }

    /**
     * @return
     */
    @Override
    public ConsultationDAO getConsultationDAO() {
        return new MyBatisConsultationDAOProxy();
    }

    /**
     * @return
     */
    @Override
    public PatientDAO getPatientDAO() {
        return new MyBatisPatientDAOProxy();
    }

    /**
     * @return
     */
    @Override
    public DoctorDAO getDoctorDAO() {
        return new MyBatisDoctorDAOProxy();
    }

    /**
     * @return
     */
    @Override
    public AdmissionRecordDAO getAdmissionRecordDAO() {
        return new MyBatisAdmissionRecordDAOProxy();
    }

    /**
     * @return
     */
    @Override
    public EmergencyContactDAO getEmergencyContactDAO() {
        return new MyBatisEmergencyContactDAOProxy();
    }

    /**
     * @return
     */
    @Override
    public InvoiceDAO getInvoiceDAO() {
        return new MyBatisInvoiceDAOProxy();
    }

    /**
     * @return
     */
    @Override
    public InvoiceHasServiceDAO getInvoiceHasServiceDAO() {
        return new MyBatisInvoiceHasServiceDAOProxy();
    }

    /**
     * @return
     */
    @Override
    public MedicineDAO getMedicineDAO() {
        return new MyBatisMedicineDAOProxy();
    }

    /**
     * @return
     */
    @Override
    public PrescriptionDAO getPrescriptionDAO() {
        return new MyBatisPrescriptionDAOProxy();
    }

    /**
     * @return
     */
    @Override
    public PrescriptionHasMedicineDAO getPrescriptionHasMedicineDAO() {
        return new MyBatisPrescriptionHasMedicineDAOProxy();
    }

    /**
     * @return
     */
    @Override
    public ServiceDAO getServiceDAO() {
        return new MyBatisServiceDAOProxy();
    }

    /**
     * @return
     */
    @Override
    public TreatmentRecordDAO getTreatmentRecordDAO() {
        return new MyBatisTreatmentRecordDAOProxy();
    }
}
