package com.higo.tinklabstest.http.web_api;

import com.google.gson.annotations.SerializedName;
import com.higo.tinklabstest.entity.CityGuide;

import java.util.List;

/**
 * Created by sharkliu on 2018/6/3.
 * api deserialize json stream refer by Gson
 */

public class GetCityGuideResponse {
    @SerializedName("Code")
    private String code;
    @SerializedName("Message")
    private String message;
    @SerializedName("CityGuides")
    List<CityGuide> cityGuides;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CityGuide> getCityGuides() {
        return cityGuides;
    }

    public void setCityGuides(List<CityGuide> cityGuides) {
        this.cityGuides = cityGuides;
    }
}
