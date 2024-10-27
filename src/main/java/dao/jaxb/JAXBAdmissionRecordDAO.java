package dao.jaxb;

import dao.interfaces.AdmissionRecordDAO;
import models.AdmissionRecord;
import models.xml.Hospital;

import java.util.List;
import java.util.Optional;

public class JAXBAdmissionRecordDAO extends JAXBWrapper implements AdmissionRecordDAO {

    @Override
    public int insert(AdmissionRecord obj) {
	logger.trace("AccessingDataBase");
        Hospital db = getDB();
        List<AdmissionRecord> records;
        try {
            records = db.getAdmissionRecords();
        }catch(NullPointerException e) {
            logger.warn("Retrieved a null record from the database", e);
            return 0;
        }
        //It is important to check latest id
        int latestId = records.getLast().getAdmissionId();
        obj.setAdmissionId(latestId+1);
        records.add(obj);
        db.setAdmissionRecords(records);
        writeDB(db);
        return 1;
    }

    @Override
    public int update(int id, AdmissionRecord obj) {
        Hospital db = getDB();
        List<AdmissionRecord> records = db.getAdmissionRecords();

        if (obj != null) {
            records.forEach((p)-> {
                if (p.getAdmissionId() == obj.getAdmissionId()) {
                    records.set(records.indexOf(p), obj);
                }
            });
            db.setAdmissionRecords(records);
            writeDB(db);
            return 1;
        }
        return 0;    }

    @Override
    public int delete(int id) {
        Hospital db = getDB();
        List<AdmissionRecord> records = db.getAdmissionRecords();
        for (AdmissionRecord record : records) {
            if (record.getAdmissionId() == id) {
                records.remove(record);
                db.setAdmissionRecords(records);
                writeDB(db);
                return 1;
            }
        }
        return 0;
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
