import java.util.HashMap;
import java.util.Map;


public class Cache<K, V> {
    final Map<K, CacheEntry<V>> cacheMap = new HashMap<>();
    private final long ttlMillis;

    public Cache(long ttlMillis) {
        this.ttlMillis = ttlMillis;
    }

    public V get(K key, DataHandler<K, V> dataHandler) {
        CacheEntry<V> cacheEntry = cacheMap.get(key);

        if (cacheEntry == null || isCacheExpired(cacheEntry)) {
            V data = dataHandler.getData(key);

            if (data != null) {
                cacheEntry = new CacheEntry<>(data, System.currentTimeMillis());
                cacheMap.put(key, cacheEntry);
            }
        }

        return (cacheEntry != null) ? cacheEntry.data() : null;
    }

    private boolean isCacheExpired(CacheEntry<V> cacheEntry) {
        long currentTime = System.currentTimeMillis();
        return currentTime - cacheEntry.timestamp() > ttlMillis;
    }
}
