package in.srain.cube.request;

public interface CacheAbleRequestHandler<T1> extends RequestHandler<T1> {

    /**
     * the data from cache, outOfDate detective if the data is out of date.
     */
    void onCacheData(T1 data, boolean outOfDate);

    void onCacheAbleRequestFinish(T1 data, CacheAbleRequest.ResultType type, boolean outOfDate);
}