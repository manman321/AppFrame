package com.wlht.oa.net;
import com.wlht.oa.bean.Menu;
import com.wlht.oa.bean.Result;
import com.wlht.oa.bean.Value;
import com.wlht.oa.bean.ValueParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Administrator on 2016/3/23.
 */
public interface ApiService {

    @FormUrlEncoded
    @POST("Login/Login")
    Observable<String> login(@Field("Account") String username,@Field("Password") String password);


    @FormUrlEncoded
    @POST("Login/Login")
    Observable<String> login(@FieldMap() HashMap<String,Object> params);




    @GET("Login/Login?iframeid=roadui_window_09344498690028487&openerid=")
    Observable<String> loginPrev();

    @GET("Home")
    Observable<String> home();


    @Headers("Cache-Control: public,max-age=3600")
    @GET("Home/Menu")
    Observable<String> menu(@QueryMap() HashMap<String,Object> params);

    @GET("Home")
    Observable<Result<Menu>> menu();


//    http://localhost:8080/test/data?page=1
//    @FormUrlEncoded
//    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
//    @POST("test/data")
//    Observable<Result<ArrayList<Value>>> data(@Body Map<String,String> param);

//    @Headers("Cache-Control: public, max-age=3600")
//    @Headers("Cache-Control: public, max-age=60,max-stale=120")
//    @FormUrlEncoded
//    @POST("test/data")
//    Observable<Result<ArrayList<Value>>> data(@FieldMap Map<String,String> param);


    @Headers("Cache-Control: public, max-age=60,max-stale=120")
    @GET("test/data")
    Observable<Result<ArrayList<Value>>> data(@QueryMap Map<String,String> param);


    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("test/data")
    Observable<Result<ArrayList<Value>>> data(@Body RequestBody param);


    @Multipart
    @PUT("/test/")
    Observable<Result<String>> upload(@Part("image") RequestBody requestBody);

    @Multipart
    @PUT("/test/")
    Observable<Result<String>> uploads(@PartMap() HashMap<String,RequestBody> requestBody,@Part("description") RequestBody description);





//    @GET("login")
//    Observable<_User> login(@Query("username") String username, @Query("password") String password);
//
//    @POST("users")
//    Observable<CreatedResult> createUser(@Body _User user);
//
//
//    @GET("users")
//    Observable<Data<_User>> getAllUser(@Query("skip") int skip, @Query("limit") int limit);
//
//    @GET("classes/Image")
//    Observable<Data<Image>> getAllImages(@Query("where") String where, @Query("order") String order, @Query("skip") int skip, @Query("limit") int limit);
//
//
//    @GET("classes/Comment")
//    Observable<Data<CommentInfo>> getCommentList(@Query("include") String include, @Query("where") String where, @Query("skip") int skip, @Query("limit") int limit);
//
//
//    @POST("classes/Comment")
//    Observable<CreatedResult> createComment(@Body Comment mComment);
//
//
//    @Headers("Content-Type: image/png")
//    @POST("files/{name}")
//    Observable<CreatedResult> upFile(@Path("name") String name, @Body RequestBody body);
//
//
//    @PUT("users/{uid}")
//    Observable<CreatedResult> upUser(@Header("X-LC-Session") String sesssion, @Path("uid") String uid, @Body UserModel.Face face);
}
