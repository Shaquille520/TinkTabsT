package com.higo.tinklabstest.features.home;

import com.higo.tinklabstest.data.guide.GuideDataResource;
import com.higo.tinklabstest.entity.CityGuide;

import java.util.List;

/**
 * Created by sharkliu on 2018/6/2.
 */

public class CityGuidePresenter implements HomeContract.Presenter{
    HomeContract.View view;
    GuideDataResource dataResource;
    private final static int SIZE=20;//load item count
    public CityGuidePresenter(HomeContract.View view,GuideDataResource dataResource){
        this.view=view;
        this.dataResource=dataResource;
        this.view.setPresenter(this);
    }
    @Override
    public void start() {
        load();
    }

    @Override
    public void onDestroy() {
        view=null;
    }

    @Override
    public void load() {
        dataResource.load(100,new GuideDataResource.LoadDataCallback(){

            @Override
            public void onDataLoaded(List<CityGuide> data) {
                if(view!=null)
                view.showData(data);
            }
            @Override
            public void noDataAvailable() {
                if(view!=null)
                view.noData();
            }
        });
    }

    @Override
    public void loadNextMore(String timeStamp) {
        dataResource.loadNextMore(timeStamp,SIZE,new GuideDataResource.LoadDataCallback(){

            @Override
            public void onDataLoaded(List<CityGuide> data) {
                if(view!=null)
                view.addMoreData(data);
            }

            @Override
            public void noDataAvailable() {
                if(view!=null)
                view.noMoreData();
            }
        });
    }
}
