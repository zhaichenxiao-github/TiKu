package com.example.tiku;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.tiku.adapter.VpAdapter;
import com.example.tiku.fragment.DownLoadFragment;
import com.example.tiku.fragment.HomeFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp;
    private TabLayout tab;
    private ArrayList<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        tab = (TabLayout) findViewById(R.id.tab);
        list = new ArrayList<>();
        HomeFragment homeFragment = new HomeFragment();
        DownLoadFragment downLoadFragment = new DownLoadFragment();
        list.add(homeFragment);
        list.add(downLoadFragment);
        VpAdapter vpAdapter = new VpAdapter(getSupportFragmentManager(), list);
        vp.setAdapter(vpAdapter);
        tab.setupWithViewPager(vp);
        tab.getTabAt(0).setText("首页");
        tab.getTabAt(1).setText("下载");
    }
}
