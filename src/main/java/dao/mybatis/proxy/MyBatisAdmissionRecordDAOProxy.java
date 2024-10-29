package dao.mybatis.proxy;

import dao.interfaces.AdmissionRecordDAO;
import dao.mybatis.MyBatisAdmissionRecordDAO;
import models.AdmissionRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static java.time.Instant.now;

public class MyBatisAdmissionRecordDAOProxy extends CacheProxy implements AdmissionRecordDAO {
    private MyBatisAdmissionRecordDAO currentDao = new MyBatisAdmissionRecordDAO();
    private Logger logger = LogManager.getLogger(MyBatisAdmissionRecordDAOProxy.class);

    private HashMap<Integer, Optional<AdmissionRecord>> lastSelected = new HashMap<>();
    private List<AdmissionRecord> lastSelectAll = null;

    /**
     * @param obj
     */
    @Override
    public int insert(AdmissionRecord obj) {
        logger.traceEntry();
        logger.debug("Attempting to insert admissionRecord.");

        int modifiedRows = currentDao.insert(obj);
        invalidateCache = modifiedRows > 0;

        return logger.traceExit(modifiedRows);
    }

    /**
     * @param id
     * @param obj
     */
    @Override
    public int update(int id, AdmissionRecord obj) {
        logger.traceEntry();
        logger.debug("Attempting to update admissionRecord.");

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
        logger.debug("Attempting to delete admissionRecord.");

        int modifiedRows = currentDao.delete(id);
        invalidateCache = modifiedRows > 0;

        return logger.traceExit(modifiedRows);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<AdmissionRecord> select(int id) {
        logger.traceEntry();
        logger.debug("Checking AdmissionRecord selectById cache.");

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
    public List<AdmissionRecord> selectAll() {
        logger.traceEntry();
        logger.debug("Checking AdmissionRecord selectById cache.");

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
