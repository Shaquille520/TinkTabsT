package com.higo.tinklabstest.data.guide;

import android.support.annotation.NonNull;

import com.higo.tinklabstest.entity.CityGuide;

import java.util.List;
import java.util.Map;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by sharkliu on 2018/6/2.
 */

public class GuideRepository implements GuideDataResource {
    private static GuideRepository INSTANCE = null;
    private GuideLocalDataResource localDataResource;
    private GuideRemoteDataResource remoteDataResource;
    private Map<String, String> mCachedGuide;

    private Map<String, List<CityGuide>> mCachedGuideList;

    private boolean mCacheIsDirty = false;

    // Prevent direct instantiation.
    private GuideRepository(@NonNull GuideLocalDataResource localDataSource,
                           @NonNull GuideRemoteDataResource remoteDataSource) {
        localDataResource = checkNotNull(localDataSource);
        remoteDataResource = checkNotNull(remoteDataSource);
    }


    public static GuideRepository getInstance(@NonNull GuideLocalDataResource localDataSource,
                                             @NonNull GuideRemoteDataResource remoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new GuideRepository(localDataSource, remoteDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void load(@NonNull final int size, @NonNull final LoadDataCallback callback) {
        //load from local,
        localDataResource.load(size, new LoadDataCallback() {
            @Override
            public void onDataLoaded(List<CityGuide> data) {

                callback.onDataLoaded(data);
            }

            @Override
            public void noDataAvailable() {
                //local form remote
                remoteDataResource.load(size, new LoadDataCallback() {
                    @Override
                    public void onDataLoaded(List<CityGuide> data) {
                        for(CityGuide guide:data) {
                            //save local
                            save(guide);
                        }
                            //load from local
                            localDataResource.load(size,callback);

                    }

                    @Override
                    public void noDataAvailable() {
                        //load from local
                        localDataResource.load(size,callback);
                    }
                });

            }
        });
    }
    public void save(CityGuide guide) {
        localDataResource.save(guide);
    }

    @Override
    public void loadNextMore(@NonNull final String timeStamp, @NonNull final int size, @NonNull final LoadDataCallback callback) {
        //first load from remote
        remoteDataResource.loadNextMore(timeStamp,size,new LoadDataCallback(){

            @Override
            public void onDataLoaded(List<CityGuide> data) {
                for(CityGuide guide:data) {
                    //save local
                    save(guide);
                }
                    //load from local
                    localDataResource.loadNextMore(timeStamp,size,callback);
            }

            @Override
            public void noDataAvailable() {
                // return from local query
                localDataResource.loadNextMore(timeStamp,size,callback);
            }
        });
    }
}
