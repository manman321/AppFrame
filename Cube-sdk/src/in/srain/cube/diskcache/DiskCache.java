package in.srain.cube.diskcache;

import java.io.File;
import java.io.IOException;

public interface DiskCache {

    /**
     * Check if has this key
     *
     * @param key
     * @return
     */
    boolean has(String key);

    /**
     * open disk cache
     *
     * @throws java.io.IOException
     */
    void open() throws IOException;

    /**
     * clear all data
     *
     * @throws java.io.IOException
     */
    void clear() throws IOException;

    /**
     * close the cache
     *
     * @throws java.io.IOException
     */
    void close() throws IOException;

    /**
     * flush data to dish
     */
    void flush() throws IOException;

    /**
     * @param key
     * @return
     * @throws java.io.IOException
     */
    CacheEntry getEntry(String key) throws IOException;

    /**
     * begin edit an {@CacheEntry }
     *
     * @param key
     * @return
     * @throws java.io.IOException
     */
    CacheEntry beginEdit(String key) throws IOException;

    /**
     * abort edit
     *
     * @param cacheEntry
     */
    void abortEdit(CacheEntry cacheEntry);

    /**
     * abort edit by key
     *
     * @param key
     */
    void abortEdit(String key);

    /**
     * abort edit by key
     */
    void commitEdit(CacheEntry cacheEntry) throws IOException;

    /**
     * delete if key exist, under edit can not be deleted
     *
     * @param key
     * @return
     */
    boolean delete(String key) throws IOException;

    long getCapacity();

    long getSize();

    File getDirectory();
}
