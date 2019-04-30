package com.purewhite.ywc.purewhitelibrary.network.retrofit.request.http;


import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * @author yuwenchao
 * 需要什么根据自己需求变化
 *
 *
 *
 * @Query、@QueryMap：用于Http Get请求传递参数
 *
 * @Field：用于Post方式传递参数,需要在请求接口方法上添加@FormUrlEncoded,即以表单的方式传递参数
 *
 * @Body：用于Post,根据转换方式将实例对象转化为对应字符串传递参数.比如Retrofit添加GsonConverterFactory则是将body转化为gson字符串进行传递
 *
 * @Path：用于URL上占位符
 *
 * @Part：配合@Multipart使用,一般用于文件上传
 *
 * @Header：添加http header
 *
 * @Headers：跟@Header作用一样,只是使用方式不一样,@Header是作为请求方法的参数传入,@Headers是以固定方式直接添加到请求方法上
 *
 */
public interface HttpService {

    @GET()
    Observable<ResponseBody> get(@Url String url, @QueryMap Map<String, Object> maps);


    @GET()
    Observable<ResponseBody> getResponBody(@Url String url, @QueryMap Map<String, String> maps);

//    @FormUrlEncoded
//    @POST("goods/querySpecialsale")
//    Observable<BaseBean<MainBean>> getShopList(@Field("shoptype") String shoptype
//            , @Field("pageSize") int pageSize, @Field("pageNo") int pageNo);
//
//
//    @GET("itemlist")
//    Observable<BaseBean<List<GoodsListBean>>> obtainGoods(@Query("cid") int position, @Query("back") int pageSise, @Query("min_id") long page);

}
