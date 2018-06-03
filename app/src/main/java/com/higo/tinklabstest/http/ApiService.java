package com.higo.tinklabstest.http;

import com.higo.tinklabstest.entity.CityGuide;
import com.higo.tinklabstest.http.web_api.GetCityGuideResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by sharkliu on 2017/11/16.
 */

public interface ApiService {

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("getCityGuideData")
    Call<GetCityGuideResponse> getCityGuideData(@Query("TimeStamp")String  timestap);

}
