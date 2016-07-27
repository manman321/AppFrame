package in.srain.cube.image.iface;

import android.graphics.drawable.BitmapDrawable;

public interface ImageMemoryCache {

    void set(String key, BitmapDrawable data);

    BitmapDrawable get(String key);

    void clear();

    void delete(String key);

    /**
     * max byte
     *
     * @return
     */
    long getMaxSize();

    /**
     * used byte
     *
     * @return
     */
    long getUsedSpace();
}
