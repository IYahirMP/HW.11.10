package dao.mybatis.proxy;

import dao.interfaces.EmergencyContactDAO;
import dao.mybatis.MyBatisEmergencyContactDAO;
import models.EmergencyContact;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static java.time.Instant.now;

public class MyBatisEmergencyContactDAOProxy extends CacheProxy implements EmergencyContactDAO {
    private MyBatisEmergencyContactDAO currentDao = new MyBatisEmergencyContactDAO();
    private Logger logger = LogManager.getLogger(MyBatisEmergencyContactDAOProxy.class);

    private HashMap<Integer, Optional<EmergencyContact>> lastSelected = new HashMap<>();
    private List<EmergencyContact> lastSelectAll = null;

    /**
     * @param obj
     */
    @Override
    public int insert(EmergencyContact obj) {
        logger.traceEntry();
        logger.debug("Attempting to insert emergencyContact.");

        int modifiedRows = currentDao.insert(obj);
        invalidateCache = modifiedRows > 0;

        return logger.traceExit(modifiedRows);
    }

    /**
     * @param id
     * @param obj
     */
    @Override
    public int update(int id, EmergencyContact obj) {
        logger.traceEntry();
        logger.debug("Attempting to update emergencyContact.");

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
        logger.debug("Attempting to delete emergencyContact.");

        int modifiedRows = currentDao.delete(id);
        invalidateCache = modifiedRows > 0;

        return logger.traceExit(modifiedRows);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<EmergencyContact> select(int id) {
        logger.traceEntry();
        logger.debug("Checking EmergencyContact selectById cache.");

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
    public List<EmergencyContact> selectAll() {
        logger.traceEntry();
        logger.debug("Checking EmergencyContact selectById cache.");

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
