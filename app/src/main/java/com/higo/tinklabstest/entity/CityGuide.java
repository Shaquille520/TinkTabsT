package com.higo.tinklabstest.entity;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by sharkliu on 2018/6/1.
 */
@Entity
public class CityGuide {
    @Id
    private Long ID;
    @SerializedName("Description")
    private String description;
    @SerializedName("Image")
    private String image;
    @SerializedName("Title")
    private String title;
    private String createTime;
    private String timeStamp; // last modify time of entity
    @SerializedName("Type")
    private int type;//0,only image,1 image+desc+name
    @Generated(hash = 1198165894)
    public CityGuide(Long ID, String description, String image, String title,
            String createTime, String timeStamp, int type) {
        this.ID = ID;
        this.description = description;
        this.image = image;
        this.title = title;
        this.createTime = createTime;
        this.timeStamp = timeStamp;
        this.type = type;
    }
    @Generated(hash = 1953282257)
    public CityGuide() {
    }
    public Long getID() {
        return this.ID;
    }
    public void setID(Long ID) {
        this.ID = ID;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getImage() {
        return this.image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public String getTimeStamp() {
        return this.timeStamp;
    }
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
}
