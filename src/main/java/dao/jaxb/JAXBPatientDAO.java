package dao.jaxb;

import dao.interfaces.PatientDAO;
import models.Patient;
import models.xml.Hospital;

import java.util.List;
import java.util.Optional;

public class JAXBPatientDAO extends JAXBWrapper implements PatientDAO {
    
    @Override
    public int insert(Patient obj) {
	logger.debug("Retrieving DataBase");
        Hospital db = getDB();
        logger.trace("Database retrieved", db);
        logger.trace("Attempting to retrieve list of patients.");
        List<Patient> records = db.getPatients();
        logger.debug("List of patients retrieved");

        //It is important to check latest id
        logger.debug("Retrieving last register's id");
        int latestId = records.getLast().getPatientId();
        logger.debug("Setting new object's id");
        obj.setPatientId(latestId+1);

        logger.trace("Adding object to list");
        records.add(obj);
        logger.debug("Replacing database list of patients");
        db.setPatients(records);
        logger.trace("Entering WriteDB");
        writeDB(db);
        return logger.traceExit(1);
    }

    @Override
    public int update(int id, Patient obj) {
        logger.debug("Retrieving DataBase");
        Hospital db = getDB();
        logger.trace("Database retrieved", db);
        logger.trace("Attempting to retrieve list of patients.");
        List<Patient> records = db.getPatients();
        logger.debug("List of patients retrieved");;

        logger.debug("Searching for patient with id: {}", id);
        if (obj != null) {
            records.forEach((p)-> {
                if (p.getPatientId() == obj.getPatientId()) {
                    logger.debug("Updating object's id");
                    records.set(records.indexOf(p), obj);
                }
            });

            db.setPatients(records);
            logger.trace("Entering WriteDB");
            writeDB(db);
            return logger.traceExit(1);
        }
        return logger.traceExit(0);    }

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
        return getDB().getPatients().stream().filter((a) -> a.getPatientId() == id).findFirst();
    }

    @Override
    public List<Patient> selectAll() {
        return getDB().getPatients();
    }


}
