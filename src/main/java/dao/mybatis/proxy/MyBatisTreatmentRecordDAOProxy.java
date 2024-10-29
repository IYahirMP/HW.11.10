package dao.mybatis.proxy;

import dao.interfaces.TreatmentRecordDAO;
import dao.mybatis.MyBatisTreatmentRecordDAO;
import models.TreatmentRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static java.time.Instant.now;

public class MyBatisTreatmentRecordDAOProxy extends CacheProxy implements TreatmentRecordDAO {
    private MyBatisTreatmentRecordDAO currentDao = new MyBatisTreatmentRecordDAO();
    private Logger logger = LogManager.getLogger(MyBatisTreatmentRecordDAOProxy.class);

    private HashMap<Integer, Optional<TreatmentRecord>> lastSelected = new HashMap<>();
    private List<TreatmentRecord> lastSelectAll = null;
    private long lastUpdate = now().getNano() / 1000_000_000;
    private boolean invalidateCache = false;

    /**
     * @param obj
     */
    @Override
    public int insert(TreatmentRecord obj) {
        logger.traceEntry();
        logger.debug("Attempting to insert treatmentRecord.");

        int modifiedRows = currentDao.insert(obj);
        invalidateCache = modifiedRows > 0;

        return logger.traceExit(modifiedRows);
    }

    /**
     * @param id
     * @param obj
     */
    @Override
    public int update(int id, TreatmentRecord obj) {
        logger.traceEntry();
        logger.debug("Attempting to update treatmentRecord.");

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
        logger.debug("Attempting to delete treatmentRecord.");

        int modifiedRows = currentDao.delete(id);
        invalidateCache = modifiedRows > 0;

        return logger.traceExit(modifiedRows);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<TreatmentRecord> select(int id) {
        logger.traceEntry();
        logger.debug("Checking TreatmentRecord selectById cache.");

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
    public List<TreatmentRecord> selectAll() {
        logger.traceEntry();
        logger.debug("Checking TreatmentRecord selectById cache.");

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
