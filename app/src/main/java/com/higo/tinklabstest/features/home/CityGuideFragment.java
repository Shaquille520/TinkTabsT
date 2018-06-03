package com.higo.tinklabstest.features.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.higo.tinklabstest.R;
import com.higo.tinklabstest.base.BaseFragment;
import com.higo.tinklabstest.base.Injection;
import com.higo.tinklabstest.entity.CityGuide;
import com.higo.tinklabstest.view.MyDecoration;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sharkliu on 2018/6/2.
 */

public class CityGuideFragment extends BaseFragment implements HomeContract.View {
    @BindView(R.id.swiperefreshlayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.no_network)
    RelativeLayout noNetwork;

    @BindView(R.id.no_data)
    RelativeLayout noData;

    private HomeContract.Presenter presenter;

    private GuideAdapter adapter;

    private Activity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    public static Fragment newInstance() {
        return new CityGuideFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         new CityGuidePresenter(this, Injection.provideGuideRepository());
        presenter.start();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_city_guide, container, false);
        ButterKnife.bind(this, view);
        initView();
        initRefresh();
        return view;
    }

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.addItemDecoration(new MyDecoration(activity, MyDecoration.VERTICAL_LIST));//添加分割线
        adapter = new GuideAdapter(null);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    private void initRefresh() {
        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(adapter!=null){
                    String timeStamp="0";
                    if(adapter.getItemCount()>0){
                        timeStamp=adapter.getItem(0).getTimeStamp();
                    }
                    refreshLayout.setRefreshing(true);
                    presenter.loadNextMore(timeStamp);
                }else{
                    refreshLayout.setRefreshing(false);
                }
            }
        });
    }

    @OnClick({R.id.no_data, R.id.no_network})
    public void refresh(View view) {
        noData.setVisibility(View.GONE);
        noNetwork.setVisibility(View.GONE);

        switch (view.getId()) {
            case R.id.no_data:
               presenter.start();
                break;
            case R.id.no_network:
              presenter.start();
                break;
        }
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void showData(List<CityGuide> cityGuides) {
        adapter.getData().clear();
        adapter.addData(cityGuides);
    }

    @Override
    public void addMoreData(List<CityGuide> cityGuides) {
        refreshLayout.setRefreshing(false);
        adapter.addData(0,cityGuides);
    }

    @Override
    public void noData() {
        noData.setVisibility(View.VISIBLE);
    }

    @Override
    public void noMoreData() {

        refreshLayout.setRefreshing(false);
    }

}
