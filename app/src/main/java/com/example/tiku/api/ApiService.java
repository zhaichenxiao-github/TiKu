package com.example.tiku.api;

import com.example.tiku.bean.Bean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Streaming;

public interface ApiService {
    String downurl="https://dl.hdslb.com/";
    String baseurl="https://www.wanandroid.com/";
    @GET("project/list/1/json?cid=294")
    Observable<Bean> getdata();
    @GET("mobile/latest/iBiliPlayer-bili.apk?t=1589783162000&spm_id_from=333.47.b_646f776e6c6f61642d6c696e6b.1")
    @Streaming
    Observable<ResponseBody> getapk();
}
