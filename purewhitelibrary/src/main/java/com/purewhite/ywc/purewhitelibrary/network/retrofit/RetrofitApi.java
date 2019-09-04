package com.purewhite.ywc.purewhitelibrary.network.retrofit;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * @author yuwenchao
 */
public interface RetrofitApi {

    @GET()
    Observable<ResponseBody> get(@Url String uri);

    @GET()
    Observable<ResponseBody> get(@Url String uri,@QueryMap Map<String,Object> map);


    @FormUrlEncoded
    @POST
    Observable<ResponseBody> post(@Url String uri,@FieldMap Map<String,Object> map);


}
