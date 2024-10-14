package deprecated.data_service;

import deprecated.dao.PatientDAO;

import javax.sql.DataSource;

import models.Patient;
import java.util.List;
import java.util.Optional;

public class PatientService {
    private PatientDAO patientDAO;

    public PatientService(DataSource dataSource) {
        this.patientDAO = new PatientDAO(dataSource);
    }

    public void savePatient(Patient patient) {
        patientDAO.save(patient);
    }

    public Optional<Patient> getPatientById(int id) {
        return patientDAO.findById(id);
    }

    public List<Patient> getAllPatients() {
        return patientDAO.findAll();
    }

    public void updatePatient(Patient patient) {
        patientDAO.update(patient);
    }

    public void deletePatient(int id) {
        patientDAO.delete(id);
    }
}
