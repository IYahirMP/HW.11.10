package dao.jaxb;

import dao.interfaces.DoctorDAO;
import models.Doctor;
import models.xml.Hospital;

import java.util.List;
import java.util.Optional;

public class JAXBDoctorDAO extends JAXBWrapper implements DoctorDAO {

    @Override
    public int insert(Doctor obj) {
	logger.trace("AccessingDataBase");
        Hospital db = getDB();
        List<Doctor> records = db.getDoctors();

        //It is important to check latest id
        int latestId = records.getLast().getDoctorId();
        obj.setDoctorId(latestId+1);

        if (obj != null) {
            records.add(obj);
            db.setDoctors(records);
            writeDB(db);
            return 1;
        }
        return 0;
    }

    @Override
    public int update(int id, Doctor obj) {
        Hospital db = getDB();
        List<Doctor> records = db.getDoctors();

        if (obj != null) {
            records.forEach((p)-> {
                if (p.getDoctorId() == obj.getDoctorId()) {
                    records.set(records.indexOf(p), obj);
                }
            });
            db.setDoctors(records);
            writeDB(db);
            return 1;
        }
        return 0;    }

    @Override
    public int delete(int id) {
        Hospital db = getDB();
        List<Doctor> records = db.getDoctors();
        for (Doctor record : records) {
            if (record.getDoctorId() == id) {
                records.remove(record);
                db.setDoctors(records);
                writeDB(db);
                return 1;
            }
        }
        return 0;
    }

    @Override
    public Optional<Doctor> select(int id) {
        return getDB().getDoctors().stream().filter((a) -> a.getDoctorId() == id).findFirst();
    }

    @Override
    public List<Doctor> selectAll() {
        return getDB().getDoctors();
    }

}
