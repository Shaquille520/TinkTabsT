package com.higo.tinklabstest.presenter;

import android.support.annotation.NonNull;

import com.higo.tinklabstest.data.guide.GuideDataResource;
import com.higo.tinklabstest.entity.CityGuide;
import com.higo.tinklabstest.http.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sharkliu on 2018/6/2.
 */

public class FackGuideRepository implements GuideDataResource {
    @Override
    public void save(CityGuide guide) {
        System.out.println("save");
    }

    @Override
    public void load(@NonNull int size, @NonNull LoadDataCallback callback) {
        System.out.println("load");
        callback.onDataLoaded(getLocalMockData());
    }

    @Override
    public void loadNextMore(@NonNull String timeStamp, @NonNull int size, @NonNull LoadDataCallback callback) {
        System.out.println("loadNextMore");
        callback.onDataLoaded(getLocalMockData());
    }
    private List<CityGuide> getLocalMockData(){
        List<CityGuide> cityGuides=new ArrayList<>();
        CityGuide cityGuide=new CityGuide();
        cityGuide.setType(0);
        cityGuides.add(cityGuide);

        cityGuide=new CityGuide();
        cityGuide.setType(1);
        cityGuides.add(cityGuide);
        cityGuide.setTitle("Local Mock");
        cityGuide.setDescription("Mock Server is not available ...please check network connect," +
                Constant.BASE_URL+"getCityGuideData..."
                + "the data is create by local just for ui test...");
        cityGuides.add(cityGuide);
        return cityGuides;
    }

}
