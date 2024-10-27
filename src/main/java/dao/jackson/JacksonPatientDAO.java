package dao.jackson;


import dao.interfaces.PatientDAO;
import models.Patient;
import models.xml.Hospital;

import java.util.List;
import java.util.Optional;
public class JacksonPatientDAO extends JacksonWrapper implements PatientDAO {

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
        logger.debug("Retrieving DataBase");
        Hospital db = getDB();
        logger.trace("Database retrieved", db);
        logger.trace("Attempting to retrieve list of patients.");
        List<Patient> records = db.getPatients();
        logger.debug("List of patients retrieved");;

        logger.debug("Searching for patient with id: {}", id);
        for (Patient record : records) {
            if (record.getPatientId() == id) {
                logger.debug("Deleting object's id");
                records.remove(record);
                db.setPatients(records);
                logger.trace("Entering WriteDB");
                writeDB(db);
                return logger.traceExit(1);
            }
        }
        return 0;
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
