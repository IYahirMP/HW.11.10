package dao.jaxb;

import dao.interfaces.ConsultationDAO;
import models.Consultation;
import models.xml.Hospital;

import java.util.List;
import java.util.Optional;

public class JAXBConsultationDAO extends JAXBWrapper implements ConsultationDAO {

    @Override
    public int insert(Consultation obj) {
	logger.trace("AccessingDataBase");
        Hospital db = getDB();
        List<Consultation> records = db.getConsultations();

        //It is important to check latest id
        int latestId = records.getLast().getConsultationId();
        obj.setConsultationId(latestId+1);

        if (obj != null) {
            records.add(obj);
            db.setConsultations(records);
            writeDB(db);
            return 1;
        }
        return 0;
    }

    @Override
    public int update(int id, Consultation obj) {
        Hospital db = getDB();
        List<Consultation> records = db.getConsultations();

        if (obj != null) {
            records.forEach((p)-> {
                if (p.getConsultationId() == obj.getConsultationId()) {
                    records.set(records.indexOf(p), obj);
                }
            });
            db.setConsultations(records);
            writeDB(db);
            return 1;
        }
        return 0;    }

    @Override
    public int delete(int id) {
        Hospital db = getDB();
        List<Consultation> records = db.getConsultations();
        for (Consultation record : records) {
            if (record.getConsultationId() == id) {
                records.remove(record);
                db.setConsultations(records);
                writeDB(db);
                return 1;
            }
        }
        return 0;
    }

    @Override
    public Optional<Consultation> select(int id) {
        return getDB().getConsultations().stream().filter((a) -> a.getConsultationId() == id).findFirst();
    }

    @Override
    public List<Consultation> selectAll() {
        return getDB().getConsultations();
    }

}
