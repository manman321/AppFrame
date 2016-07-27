package retrofit2;

import com.google.gson.TypeAdapter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;

final class ByteResponseBodyConverter<T> implements Converter<ResponseBody, T> {
  private final TypeAdapter<T> adapter;

  ByteResponseBodyConverter(TypeAdapter<T> adapter) {
    this.adapter = adapter;
  }

  @Override
  public T convert(ResponseBody value) throws IOException {
    InputStream reader = value.byteStream();
    try {
      ByteArrayOutputStream writer = new ByteArrayOutputStream();
      byte[] buffer = new byte[1024 * 8];
      int size = -1;
      while ((size = reader.read(buffer)) > 0)
      {
        writer.write(buffer,0,size);
      }
      return (T)(writer.toByteArray());
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException ignored) {
        }
      }
    }
  }
}