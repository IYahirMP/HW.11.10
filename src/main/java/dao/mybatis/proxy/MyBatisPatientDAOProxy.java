package dao.mybatis.proxy;

import dao.interfaces.PatientDAO;
import dao.mybatis.MyBatisPatientDAO;
import models.Patient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static java.time.Instant.now;

public class MyBatisPatientDAOProxy extends CacheProxy implements PatientDAO {
    private MyBatisPatientDAO currentDao = new MyBatisPatientDAO();
    private Logger logger = LogManager.getLogger(MyBatisPatientDAOProxy.class);

    private HashMap<Integer, Optional<Patient>> lastSelected = new HashMap<>();
    private List<Patient> lastSelectAll = null;
    
    /**
     * @param obj
     */
    @Override
    public int insert(Patient obj) {
        logger.traceEntry();
        logger.debug("Attempting to insert patient.");

        int modifiedRows = currentDao.insert(obj);
        invalidateCache = modifiedRows > 0;

        return logger.traceExit(modifiedRows);
    }

    /**
     * @param id
     * @param obj
     */
    @Override
    public int update(int id, Patient obj) {
        logger.traceEntry();
        logger.debug("Attempting to update patient.");

        int modifiedRows = currentDao.update(id, obj);
        invalidateCache = modifiedRows > 0;

        return logger.traceExit(modifiedRows);
    }

    /**
     * @param id
     */
    @Override
    public int delete(int id) {
        logger.traceEntry();
        logger.debug("Attempting to delete patient.");

        int modifiedRows = currentDao.delete(id);
        invalidateCache = modifiedRows > 0;

        return logger.traceExit(modifiedRows);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<Patient> select(int id) {
        logger.traceEntry();
        logger.debug("Checking Patient selectById cache.");
        
        if (lastSelected.get(id) == null || needsUpdate()){
            logger.debug("Result not cached, attempting to query database.");
            lastSelected.put(id, currentDao.select(id));
            setLastUpdate();
            return lastSelected.get(id);
        }

        logger.debug("Cache exists. Returning cached result.");
        return logger.traceExit(lastSelected.get(id));
    }

    /**
     * @return
     */
    @Override
    public List<Patient> selectAll() {
        logger.traceEntry();
        logger.debug("Checking Patient selectById cache.");

        if (lastSelectAll == null || needsUpdate()){
            logger.debug("Result not cached, attempting to query database.");
            lastSelectAll = currentDao.selectAll();
            setLastUpdate();
            return lastSelectAll;
        }

        logger.debug("Cache exists. Returning cached result.");
        return logger.traceExit(lastSelectAll);
    }

    public boolean needsUpdate(){
        boolean timedOut = now().getNano() / 1000_000_000 - lastUpdate > 30;

        return timedOut || invalidateCache;
    }

    public void setLastUpdate(){
        lastUpdate = now().getNano() / 1000_000_000;
    }
}
