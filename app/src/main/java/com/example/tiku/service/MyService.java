package com.example.tiku.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import com.example.tiku.api.ApiService;
import com.example.tiku.bean.MessageEv;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyService extends Service {
    public MyService() {
    }
    @Override
    public void onCreate() {
        downLoad();
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    private void downLoad(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.downurl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<ResponseBody> getapk = apiService.getapk();
        getapk.subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        long l = responseBody.contentLength();
                        InputStream inputStream = responseBody.byteStream();
                        saveFile(inputStream,l, Environment.getExternalStorageDirectory()+"/111.apk");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("tag", "onError: "+e.getMessage() );
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void saveFile(InputStream inputStream, long l, String s) {
        int count=1;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(s));
            byte[] bytes = new byte[1024 * 10];
            int len;
            while ((len=inputStream.read(bytes))!=-1){
                fileOutputStream.write(bytes,0,len);
                count+=len;
                EventBus.getDefault().post(new MessageEv(count, l));
            }
            inputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
