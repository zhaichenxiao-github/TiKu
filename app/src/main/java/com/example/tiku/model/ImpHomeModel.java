package com.example.tiku.model;

import android.util.Log;

import com.example.tiku.api.ApiService;
import com.example.tiku.bean.Bean;
import com.example.tiku.callback.HomeCallBack;
import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImpHomeModel implements HomeModel{
    @Override
    public void homeData(final HomeCallBack homeCallBack) {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(ApiService.baseurl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = build.create(ApiService.class);
        Observable<Bean> observable = apiService.getdata();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bean bean) {
                        Log.e("tag", "获取到的数据： "+bean.getErrorMsg());
                        homeCallBack.OnSuccess(bean);
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
}
