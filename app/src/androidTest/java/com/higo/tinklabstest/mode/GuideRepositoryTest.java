package com.higo.tinklabstest.mode;

import android.support.test.runner.AndroidJUnit4;

import com.higo.tinklabstest.base.Injection;
import com.higo.tinklabstest.data.guide.GuideDataResource;
import com.higo.tinklabstest.data.guide.GuideRepository;
import com.higo.tinklabstest.entity.CityGuide;
import com.higo.tinklabstest.utils.TimeUtil;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

/**
 *model part of mvp, test data
 * Created by sharkliu on 2018/6/3.
 */
@RunWith(AndroidJUnit4.class)

public class GuideRepositoryTest {
    GuideRepository repository= Injection.provideGuideRepository();
    @Test
    public void saveTest(){
        CityGuide cityGuide=new CityGuide();
        String desc="desc---test";
        cityGuide.setType(1);
        cityGuide.setDescription(desc);
        repository.save(cityGuide);
    }
    @Test
    public void load(){
       repository.load(1, new GuideDataResource.LoadDataCallback() {
           @Override
           public void onDataLoaded(List<CityGuide> data) {
               Assert.assertEquals(1,data.size());
           }

           @Override
           public void noDataAvailable() {
           }
       });
    }
    @Test
    public void loadNextMore(){
        /**
         *
         */
        repository.loadNextMore(TimeUtil.getCurrentTimeStamp(), 2, new GuideDataResource.LoadDataCallback() {
            @Override
            public void onDataLoaded(List<CityGuide> data) {
                Assert.assertEquals(2,data.size());
            }

            @Override
            public void noDataAvailable() {
                Assert.assertEquals(2,-1);//faild
            }
        });
    }
}
