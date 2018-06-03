package com.higo.tinklabstest.presenter;

import com.higo.tinklabstest.entity.CityGuide;
import com.higo.tinklabstest.features.home.HomeContract;

import java.util.List;

/**
 * Created by sharkliu on 2018/6/2.
 */

public class MockView implements HomeContract.View {
    HomeContract.Presenter presenter;
    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        this.presenter=presenter;
    }

    @Override
    public void showData(List<CityGuide> cityGuides) {
        System.out.println("showData"+cityGuides.size());
    }

    @Override
    public void addMoreData(List<CityGuide> cityGuides) {
        System.out.println("addMoreData:city"+cityGuides.size());
    }

    @Override
    public void noData() {
        System.out.println("NO DATA");
    }

    @Override
    public void noMoreData() {
        System.out.println("NO NoMoreData");
    }
    private final static String TAG="MockView";
}
