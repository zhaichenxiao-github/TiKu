package com.example.tiku.fragment;


import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tiku.R;
import com.example.tiku.bean.MessageEv;
import com.example.tiku.service.MyService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * A simple {@link Fragment} subclass.
 */
public class DownLoadFragment extends Fragment implements View.OnClickListener {


    private ProgressBar pb;
    private TextView tv;
    private Button btn_down;

    public DownLoadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_down_load, container, false);
        EventBus.getDefault().register(this);
        initView(view);
        return view;
    }

    private void initView(View view) {
        pb=view.findViewById(R.id.pb);
        tv=view.findViewById(R.id.tv);
        btn_down=view.findViewById(R.id.btn_down);
        btn_down.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(),MyService.class);
        getActivity().startService(intent);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void message(MessageEv messageEv){
        long count = messageEv.getCount();
        long contentgh = messageEv.getContentgh();
        pb.setMax((int) contentgh);
        pb.setProgress((int) count);
        tv.setText((int) 100f*count/contentgh+"%");
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }
}
