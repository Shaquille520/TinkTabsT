package com.higo.tinklabstest.data.guide;

import android.icu.text.MessagePattern;
import android.support.annotation.NonNull;

import com.google.gson.JsonSyntaxException;
import com.higo.tinklabstest.base.BaseApplication;
import com.higo.tinklabstest.entity.CityGuide;
import com.higo.tinklabstest.http.ApiService;
import com.higo.tinklabstest.http.Constant;
import com.higo.tinklabstest.http.HttpHelper;
import com.higo.tinklabstest.http.web_api.GetCityGuideResponse;
import com.higo.tinklabstest.utils.AppExecutors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

/**
 * Created by sharkliu on 2018/6/2.
 */

public class GuideRemoteDataResource implements GuideDataResource{

    @Override
    public void save(CityGuide guide) {
        // do nothing
    }

    @Override
    public void load(@NonNull int size, @NonNull final LoadDataCallback callback) {
        //fetch data from remote
        mAppExecutors.networkIO().execute(new Runnable() {
            @Override
            public void run() {
                List<CityGuide> data=getDataFromServer();
                if(data!=null &&!data.isEmpty()){
                    callback.onDataLoaded(data);
                }else{
                    callback.onDataLoaded(getLocalMockData());
                }

            }
        });
    }

    /**
     * fetch data from mock server
     * @return
     */
    private List<CityGuide> getDataFromServer(){
        HttpHelper httpHelper=new HttpHelper();
        try {
            Response<GetCityGuideResponse> response=httpHelper.getService(ApiService.class)
                    .getCityGuideData("")
                    .execute();
            if(response.body()==null){
                return null;
            }else{
                GetCityGuideResponse body=response.body();
                if(Constant.SUCCESS_CODE.equals(body.getCode())){
                    return body.getCityGuides();
                }else{
                    return null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }catch (JsonSyntaxException e){
            e.printStackTrace();
            return null;
        }
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

    @Override
    public void loadNextMore(@NonNull String timeStamp, @NonNull int size,final  @NonNull LoadDataCallback callback) {
         //fetch data from remote
        mAppExecutors.networkIO().execute(new Runnable() {
            @Override
            public void run() {
                List<CityGuide> data=getDataFromServer();
                if(data!=null &&!data.isEmpty()){
                    callback.onDataLoaded(data);
                }else{
                    callback.onDataLoaded(getLocalMockData());
                }

            }
        });
    }

    private static GuideRemoteDataResource INSTANCE;
    private AppExecutors mAppExecutors;

    public static GuideRemoteDataResource getInstance(@NonNull AppExecutors mAppExecutors) {
        if (INSTANCE == null) {
            INSTANCE = new GuideRemoteDataResource(mAppExecutors);
        }
        return INSTANCE;
    }

    private GuideRemoteDataResource(@NonNull AppExecutors mAppExecutors) {
        this.mAppExecutors = mAppExecutors;
    }
}
