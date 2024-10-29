package dao.mybatis.proxy;

import dao.interfaces.PrescriptionHasMedicineDAO;
import dao.mybatis.MyBatisPrescriptionHasMedicineDAO;
import models.PrescriptionHasMedicine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static java.time.Instant.now;

public class MyBatisPrescriptionHasMedicineDAOProxy extends CacheProxy implements PrescriptionHasMedicineDAO {
    private MyBatisPrescriptionHasMedicineDAO currentDao = new MyBatisPrescriptionHasMedicineDAO();
    private Logger logger = LogManager.getLogger(MyBatisPrescriptionHasMedicineDAOProxy.class);

    private HashMap<SimpleEntry<Integer, Integer>, Optional<PrescriptionHasMedicine>> lastSelectedPrescriptionHasMedicine = new HashMap<>();
    private List<PrescriptionHasMedicine> lastSelectAll = null;
    private HashMap<Integer, List<PrescriptionHasMedicine>> lastSelectAllByPrescription = new HashMap<>();
    private HashMap<Integer, List<PrescriptionHasMedicine>> lastSelectAllByMedicine = new HashMap<>();

    /**
     * @param obj
     */
    @Override
    public int insert(PrescriptionHasMedicine obj) {
        logger.traceEntry();
        logger.debug("Attempting to insert object.");
        return logger.traceExit(currentDao.insert(obj));
    }

    /**
     * @param obj
     */
    @Override
    public int update(PrescriptionHasMedicine obj) {
        logger.traceEntry();
        logger.debug("Attempting to update object.");

        int modifiedRows = currentDao.update(obj);
        invalidateCache = modifiedRows > 0;

        return logger.traceExit(modifiedRows);
    }

    @Override
    public int delete(int prescriptionId, int medicineId) {
        logger.traceEntry();
        logger.debug("Attempting to delete object.");

        int deletedId = currentDao.delete(prescriptionId, medicineId);
        invalidateCache = deletedId > 0;

        return logger.traceExit(deletedId);
    }

    @Override
    public Optional<PrescriptionHasMedicine> select(int prescriptionId, int medicineId) {
        logger.traceEntry();
        logger.debug("Checking PrescriptionHasMedicine selectById cache.");

        SimpleEntry<Integer, Integer> ids = new SimpleEntry<>(prescriptionId, medicineId);

        if (lastSelectedPrescriptionHasMedicine.get(ids) == null || needsUpdate()){
            logger.debug("Result not cached, attempting to query database.");
            lastSelectedPrescriptionHasMedicine.put(ids, currentDao.select(prescriptionId, medicineId));
            setLastUpdate();
            return lastSelectedPrescriptionHasMedicine.get(ids);
        }

        logger.debug("Cache exists. Returning cached result.");
        return logger.traceExit(lastSelectedPrescriptionHasMedicine.get(ids));
    }

    /**
     * @return
     */
    @Override
    public List<PrescriptionHasMedicine> selectAll() {
        logger.traceEntry();
        logger.debug("Checking PrescriptionHasMedicine selectAll cache.");
        if (lastSelectAll == null || needsUpdate()){
            logger.debug("Result not cached, attempting to query database.");
            lastSelectAll = currentDao.selectAll();
            setLastUpdate();
            return lastSelectAll;
        }

        logger.debug("Cache exists. Returning cached result.");
        return logger.traceExit(lastSelectAll);
    }

    /**
     * @param prescriptionId
     * @return
     */
    @Override
    public List<PrescriptionHasMedicine> selectByPrescription(int prescriptionId) {
        logger.traceEntry();
        logger.debug("Checking PrescriptionHasMedicine selectByPrescription cache.");

        // If there is no result cached for given prescription
        if (lastSelectAllByPrescription.get(prescriptionId) == null || needsUpdate()){
            logger.debug("Result not cached, attempting to query database.");
            //Cache the results for the given prescriptionId
            lastSelectAllByPrescription.put(prescriptionId, currentDao.selectByPrescription(prescriptionId));
            //Reset last update time
            setLastUpdate();
            //Return cached results
            return lastSelectAllByPrescription.get(prescriptionId);
        }
        // There exist a cached result and update time hasn't been reached

        logger.debug("Cache exists. Returning cached result.");
        //Returns cached result
        return logger.traceExit(lastSelectAllByPrescription.get(prescriptionId));
    }

    /**
     * @param medicineId
     * @return
     */
    @Override
    public List<PrescriptionHasMedicine> selectByMedicine(int medicineId) {
        logger.traceEntry();
        logger.debug("Checking PrescriptionHasMedicine selectByPrescription cache.");

        // If there is no result cached for given prescription
        if (lastSelectAllByMedicine.get(medicineId) == null || needsUpdate()){
            logger.debug("Result not cached, attempting to query database.");
            //Cache the results for the given prescriptionId
            lastSelectAllByMedicine.put(medicineId, currentDao.selectByMedicine(medicineId));
            //Reset last update time
            setLastUpdate();
            //Return cached results
            return lastSelectAllByMedicine.get(medicineId);
        }
        // There exist a cached result and update time hasn't been reached

        logger.debug("Cache exists. Returning cached result.");
        //Returns cached result
        return logger.traceExit(lastSelectAllByMedicine.get(medicineId));
    }
}
