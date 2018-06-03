package com.higo.tinklabstest.data.guide;

import android.support.annotation.NonNull;

import com.higo.tinklabstest.entity.CityGuide;

import java.util.List;

/**
 * Created by sharkliu on 2018/6/2.
 */

public interface GuideDataResource {



    interface LoadDataCallback{
        void onDataLoaded(List<CityGuide> data);
        void noDataAvailable();
    }
    void save(CityGuide guide);
    /**
     * first Load;
     * @param size
     */
    void load(@NonNull int size, @NonNull LoadDataCallback callback);

    /**
     * refresh to load more new data from remote or local database
     * @param timeStamp
     * @param size data count
     */
    void loadNextMore(@NonNull String timeStamp, @NonNull int size, @NonNull LoadDataCallback callback);

}
