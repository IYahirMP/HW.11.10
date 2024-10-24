package dao.jackson;

import dao.interfaces.AdmissionRecordDAO;
import models.AdmissionRecord;

import java.util.List;
import java.util.Optional;
import static dao.factories.JacksonDAOFactory.getDB;

public class JacksonAdmissionRecordDAO implements AdmissionRecordDAO {

    @Override
    public int insert(AdmissionRecord obj) {
        throw new UnsupportedOperationException("STAXAdmissionRecordDAO does not support insert operations.");
    }

    @Override
    public int update(int id, AdmissionRecord obj) {
        throw new UnsupportedOperationException("STAXAdmissionRecordDAO does not support update operations.");
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("STAXAdmissionRecordDAO does not support delete operations.");
    }

    @Override
    public Optional<AdmissionRecord> select(int id) {
        return getDB().getAdmissionRecords().stream().filter((a) -> a.getAdmissionId() == id).findFirst();
    }

    @Override
    public List<AdmissionRecord> selectAll() {
        return getDB().getAdmissionRecords();
    }

}
