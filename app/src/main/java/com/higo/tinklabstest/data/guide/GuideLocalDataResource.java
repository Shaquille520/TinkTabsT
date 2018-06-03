package com.higo.tinklabstest.data.guide;

import android.support.annotation.NonNull;

import com.higo.tinklabstest.data.gen.CityGuideDao;
import com.higo.tinklabstest.data.gen.DaoSession;
import com.higo.tinklabstest.entity.CityGuide;
import com.higo.tinklabstest.utils.AppExecutors;
import com.higo.tinklabstest.utils.TimeUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by sharkliu on 2018/6/2.
 */

public class GuideLocalDataResource implements GuideDataResource {
    private static GuideLocalDataResource INSTANCE;

    private CityGuideDao cityGuideDao;

    private AppExecutors mAppExecutors;

    // Prevent direct instantiation.
    private GuideLocalDataResource(@NonNull AppExecutors appExecutors,
                              @NonNull DaoSession daoSession) {
        mAppExecutors = appExecutors;
        this.cityGuideDao = daoSession.getCityGuideDao();
    }

    public static GuideLocalDataResource getInstance(@NonNull AppExecutors appExecutors,
                                                @NonNull DaoSession daoSession) {
        if (INSTANCE == null) {
            synchronized (GuideLocalDataResource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new GuideLocalDataResource(appExecutors, daoSession);
                }
            }
        }
        return INSTANCE;
    }
    @Override
    public void save(CityGuide guide) {
        guide.setCreateTime(TimeUtil.getDateTime());//set create date time
        guide.setTimeStamp(TimeUtil.getCurrentTimeStamp());//set time stamp;
        cityGuideDao.save(guide);
    }

    @Override
    public void load(@NonNull final int size, @NonNull final LoadDataCallback callback) {

            mAppExecutors.mainThread().execute(new Runnable() {
                @Override
                public void run() {
                    final List<CityGuide> cityGuideList=cityGuideDao.queryBuilder().limit(size).orderDesc(CityGuideDao.Properties.TimeStamp).list();
                    if(cityGuideList!=null &&!cityGuideList.isEmpty()) {
                        callback.onDataLoaded(cityGuideList);
                    }else{
                        callback.noDataAvailable();
                    }
                }
            });

    }

    @Override
    public void loadNextMore(@NonNull final String timeStamp, @NonNull final int size, @NonNull final LoadDataCallback callback) {

             // Collections.reverse(cityGuideList);
            mAppExecutors.mainThread().execute(new Runnable() {
                @Override
                public void run() {
                    final List<CityGuide> cityGuideList=cityGuideDao.queryBuilder().limit(size)
                            .where(CityGuideDao.Properties.TimeStamp.gt(timeStamp))
                            .orderDesc(CityGuideDao.Properties.TimeStamp).list();
                    if(cityGuideList!=null&&!cityGuideList.isEmpty()){
                          callback.onDataLoaded(cityGuideList);
                    }else{
                         callback.noDataAvailable();
                    }
                }
            });



    }
}
