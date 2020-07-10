package com.example.tiku.presenter;

import com.example.tiku.bean.Bean;
import com.example.tiku.callback.HomeCallBack;
import com.example.tiku.model.ImpHomeModel;
import com.example.tiku.view.HomeView;

public class ImpHomePresenter implements HomePresenter, HomeCallBack {
    private HomeView homeView;
    private ImpHomeModel impHomeModel;

    public ImpHomePresenter(HomeView homeView) {
        this.homeView = homeView;
        impHomeModel=new ImpHomeModel();
    }

    @Override
    public void home() {
        impHomeModel.homeData(this);
    }

    @Override
    public void OnSuccess(Bean bean) {
        homeView.OnSuccess(bean);
    }

    @Override
    public void OnFail(String error) {
        homeView.OnFail(error);
    }
}
