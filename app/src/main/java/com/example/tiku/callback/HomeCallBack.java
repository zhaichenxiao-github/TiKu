package com.example.tiku.callback;

import com.example.tiku.bean.Bean;

public interface HomeCallBack {
    void OnSuccess(Bean bean);
    void OnFail(String error);
}
