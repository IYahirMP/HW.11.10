package dao.jackson;

import dao.interfaces.ConsultationDAO;
import models.Consultation;

import java.util.List;
import java.util.Optional;

public class JacksonConsultationDAO extends JacksonWrapper implements ConsultationDAO {

    @Override
    public int insert(Consultation obj) {
        throw new UnsupportedOperationException("STAXConsultationDAO does not support insert operations.");
    }

    @Override
    public int update(int id, Consultation obj) {
        throw new UnsupportedOperationException("STAXConsultationDAO does not support update operations.");
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("STAXConsultationDAO does not support delete operations.");
    }

    @Override
    public Optional<Consultation> select(int id) {
        return getDB().getConsultations().stream().filter((c) -> c.getConsultationId() == id).findFirst();
    }

    @Override
    public List<Consultation> selectAll() {
        return getDB().getConsultations();
    }

}
