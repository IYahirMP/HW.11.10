package dao.jackson;


import dao.interfaces.PatientDAO;
import models.Patient;
import java.util.List;
import java.util.Optional;

import static dao.factories.JacksonDAOFactory.getDB;

public class JacksonPatientDAO implements PatientDAO {

    @Override
    public int insert(Patient obj) {
        throw new UnsupportedOperationException("STAXPatientDAO does not support insert operations.");
    }

    @Override
    public int update(int id, Patient obj) {
        throw new UnsupportedOperationException("STAXPatientDAO does not support update operations.");
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("STAXPatientDAO does not support delete operations.");
    }

    @Override
    public Optional<Patient> select(int id) {
        return getDB().getPatients().stream().filter((p) -> p.getPatientId()==id).findFirst();
    }

    @Override
    public List<Patient> selectAll() {
        return getDB().getPatients();
    }


}
