package dao.mybatis.proxy;

import dao.interfaces.InvoiceHasServiceDAO;
import dao.mybatis.MyBatisInvoiceHasServiceDAO;
import models.InvoiceHasService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static java.time.Instant.now;

public class MyBatisInvoiceHasServiceDAOProxy extends CacheProxy implements InvoiceHasServiceDAO {
    private MyBatisInvoiceHasServiceDAO currentDao = new MyBatisInvoiceHasServiceDAO();
    private Logger logger = LogManager.getLogger(MyBatisInvoiceHasServiceDAOProxy.class);

    private HashMap<SimpleEntry<Integer, Integer>, Optional<InvoiceHasService>> lastSelectedInvoiceHasService = new HashMap<>();
    private List<InvoiceHasService> lastSelectAll = null;
    private HashMap<Integer, List<InvoiceHasService>> lastSelectAllByInvoice = new HashMap<>();
    private HashMap<Integer, List<InvoiceHasService>> lastSelectAllByService = new HashMap<>();


    /**
     * @param obj
     */
    @Override
    public int insert(InvoiceHasService obj) {
        logger.traceEntry();
        logger.debug("Attempting to insert object.");

        int modifiedRows = currentDao.insert(obj);
        invalidateCache = modifiedRows > 0;

        return logger.traceExit(modifiedRows);
    }

    /**
     * @param obj
     */
    @Override
    public int update(InvoiceHasService obj) {
        logger.traceEntry();
        logger.debug("Attempting to update object.");

        int modifiedRows = currentDao.update(obj);
        invalidateCache = modifiedRows > 0;

        return logger.traceExit(modifiedRows);
    }

    @Override
    public int delete(int invoiceId, int serviceId) {
        logger.traceEntry();
        logger.debug("Attempting to delete object.");

        int deletedId = currentDao.delete(invoiceId, serviceId);
        invalidateCache = deletedId > 0;

        return logger.traceExit(deletedId);
    }

    @Override
    public Optional<InvoiceHasService> select(int invoiceId, int serviceId) {
        logger.traceEntry();
        logger.debug("Checking InvoiceHasService selectById cache.");

        SimpleEntry<Integer, Integer> ids = new SimpleEntry<>(invoiceId, serviceId);

        if (lastSelectedInvoiceHasService.get(ids) == null || needsUpdate()){
            logger.debug("Result not cached, attempting to query database.");
            lastSelectedInvoiceHasService.put(ids, currentDao.select(invoiceId, serviceId));
            setLastUpdate();
            return lastSelectedInvoiceHasService.get(ids);
        }

        logger.debug("Cache exists. Returning cached result.");
        return logger.traceExit(lastSelectedInvoiceHasService.get(ids));
    }

    /**
     * @return
     */
    @Override
    public List<InvoiceHasService> selectAll() {
        logger.traceEntry();
        logger.debug("Checking InvoiceHasService selectAll cache.");
        if (lastSelectAll == null || needsUpdate()){
            logger.debug("Result not cached, attempting to query database.");
            lastSelectAll = currentDao.selectAll();
            setLastUpdate();
            return lastSelectAll;
        }

        logger.debug("Cache exists. Returning cached result.");
        return logger.traceExit(currentDao.selectAll());
    }
    
    /**
     * @param invoiceId
     * @return
     */
    @Override
    public List<InvoiceHasService> selectByInvoice(int invoiceId) {
        logger.traceEntry();
        logger.debug("Checking InvoiceHasService selectByInvoice cache.");

        // If there is no result cached for given invoice
        if (lastSelectAllByInvoice.get(invoiceId) == null || needsUpdate()){
            logger.debug("Result not cached, attempting to query database.");
            //Cache the results for the given invoiceId
            lastSelectAllByInvoice.put(invoiceId, currentDao.selectByInvoice(invoiceId));
            //Reset last update time
            setLastUpdate();
            //Return cached results
            return lastSelectAllByInvoice.get(invoiceId);
        }
        // There exist a cached result and update time hasn't been reached

        logger.debug("Cache exists. Returning cached result.");
        //Returns cached result
        return logger.traceExit(lastSelectAllByInvoice.get(invoiceId));
    }

    /**
     * @param serviceId
     * @return
     */
    @Override
    public List<InvoiceHasService> selectByService(int serviceId) {
        logger.traceEntry();
        logger.debug("Checking InvoiceHasService selectByInvoice cache.");

        // If there is no result cached for given invoice
        if (lastSelectAllByService.get(serviceId) == null || needsUpdate()){
            logger.debug("Result not cached, attempting to query database.");
            //Cache the results for the given invoiceId
            lastSelectAllByService.put(serviceId, currentDao.selectByService(serviceId));
            //Reset last update time
            setLastUpdate();
            //Return cached results
            return lastSelectAllByService.get(serviceId);
        }
        // There exist a cached result and update time hasn't been reached

        logger.debug("Cache exists. Returning cached result.");
        //Returns cached result
        return logger.traceExit(lastSelectAllByService.get(serviceId));
    }
}
