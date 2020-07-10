package com.example.tiku.view;

import com.example.tiku.bean.Bean;

public interface HomeView {
    void OnSuccess(Bean bean);
    void OnFail(String error);
}
