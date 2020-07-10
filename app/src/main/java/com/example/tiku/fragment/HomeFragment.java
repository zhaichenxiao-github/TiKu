package com.example.tiku.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiku.R;
import com.example.tiku.adapter.HomeAdapter;
import com.example.tiku.bean.Bean;
import com.example.tiku.presenter.ImpHomePresenter;
import com.example.tiku.view.HomeView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeView {


    private RecyclerView rv;
    private ArrayList<Bean.DataBean.DatasBean> list;
    private HomeAdapter homeAdapter;
    private ImpHomePresenter presenter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        presenter = new ImpHomePresenter(this);
        initView(view);
        ininData();
        return view;
    }

    private void ininData() {
        presenter.home();
    }

    private void initView(View view) {
        rv=view.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.addItemDecoration(new DividerItemDecoration(getActivity(), RecyclerView.VERTICAL));
        list = new ArrayList<>();
        homeAdapter = new HomeAdapter(getActivity(), list);
        rv.setAdapter(homeAdapter);
    }

    @Override
    public void OnSuccess(Bean bean) {
        Bean.DataBean data = bean.getData();
        List<Bean.DataBean.DatasBean> datas = data.getDatas();
        list.addAll(datas);
        homeAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnFail(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }
}
