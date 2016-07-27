package in.srain.cube.request.sender;

import in.srain.cube.request.IRequest;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URLDecoder;

public class PostRequestSender extends BaseRequestSender {

    public PostRequestSender(IRequest<?> request, HttpURLConnection httpURLConnection) {
        super(request, httpURLConnection);
    }

    @Override
    public void setup() throws IOException {
        mHttpURLConnection.setRequestProperty("Content-Type", "application/octet-stream");
        mHttpURLConnection.setRequestMethod("POST");
        /*
         *  修改过 通过流的方式将参数发送到服务端
         *  如果后台修改了通过post参数的形式也可以,将这一行注释掉，
         *  然后在传参的时候加上参数名就可以了
         */
        mHttpURLConnection.setDoOutput(true);
        mHttpURLConnection.setDoInput(true);
        super.setup();
    }

    @Override
    public void send() throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(mHttpURLConnection.getOutputStream());
        String postData = mRequestData.getPostString();
//        String postString = URLDecoder.decode(postData, "UTF-8");
        writer.write(postData);
        writer.flush();
    }
}
