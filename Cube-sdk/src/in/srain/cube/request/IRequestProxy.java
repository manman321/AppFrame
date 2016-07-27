package in.srain.cube.request;

public interface IRequestProxy {

    <T> T requestSync(IRequest<T> request);

    <T> void sendRequest(IRequest<T> request);

    void prepareRequest(RequestBase request);

    void onRequestFail(RequestBase request, FailData failData);

    JsonData processOriginDataFromServer(RequestBase request, final JsonData data);
}
