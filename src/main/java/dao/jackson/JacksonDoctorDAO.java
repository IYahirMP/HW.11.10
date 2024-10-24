package dao.jackson;

import dao.interfaces.DoctorDAO;
import models.Doctor;

import java.util.List;
import java.util.Optional;
import static dao.factories.JacksonDAOFactory.getDB;


public class JacksonDoctorDAO implements DoctorDAO {

    @Override
    public int insert(Doctor obj) {
        throw new UnsupportedOperationException("STAXDoctorDAO does not support insert operations.");
    }

    @Override
    public int update(int id, Doctor obj) {
        throw new UnsupportedOperationException("STAXDoctorDAO does not support update operations.");
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("STAXDoctorDAO does not support delete operations.");
    }

    @Override
    public Optional<Doctor> select(int id) {
        return getDB().getDoctors().stream().filter((d) -> d.getDoctorId() == id).findFirst();
    }

    @Override
    public List<Doctor> selectAll() {
        return getDB().getDoctors();
    }

}
