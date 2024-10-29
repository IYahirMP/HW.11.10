package dao.mybatis.proxy;

import static java.time.Instant.now;

public abstract class CacheProxy {
    protected long lastUpdate = now().getNano() / 1000_000_000;
    protected boolean invalidateCache = false;

    public boolean needsUpdate(){
        boolean timedOut = now().getNano() / 1000_000_000 - lastUpdate > 30;

        return timedOut || invalidateCache;
    }

    public void setLastUpdate(){
        lastUpdate = now().getNano() / 1000_000_000;
    }
}
