package com.higo.tinklabstest.features.home;

import com.higo.tinklabstest.base.BasePresenter;
import com.higo.tinklabstest.base.BaseView;
import com.higo.tinklabstest.entity.CityGuide;

import java.util.List;

/**
 * Created by sharkliu on 2018/6/2.
 */

public interface HomeContract {


    interface View extends BaseView<Presenter>{

        void showData(List<CityGuide> cityGuides);
        void addMoreData(List<CityGuide> cityGuides);
        void noData();
        void noMoreData();
    }
    interface Presenter extends BasePresenter {
        /**
         *
         */
        void load();

        /**
         * get moredata
         * @param timeStamp
         */
        void loadNextMore(String timeStamp);

    }
}
