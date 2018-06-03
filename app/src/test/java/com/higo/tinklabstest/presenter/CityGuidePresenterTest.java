package com.higo.tinklabstest.presenter;

import com.higo.tinklabstest.base.Injection;
import com.higo.tinklabstest.data.guide.GuideRepository;
import com.higo.tinklabstest.features.home.CityGuidePresenter;
import com.higo.tinklabstest.utils.TimeUtil;

import org.junit.Before;
import org.junit.Test;

/**
 * presenter is contract view and model, just test call path
 * Created by sharkliu on 2018/6/2.
 */

public class CityGuidePresenterTest {
    CityGuidePresenter presenter;
    FackGuideRepository guideRepository;
    @Before
    public void setFirst(){
        guideRepository= new FackGuideRepository();
        presenter=new CityGuidePresenter(new MockView(),guideRepository);
    }
    @Test
    public void load(){
        presenter.load();
    }
    @Test
    public void loadMore(){
        presenter.loadNextMore(TimeUtil.getCurrentTimeStamp());
    }
}
