package com.nytmp.vipin.nytimesmostpopulars.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vipin Vasu on 21/02/18.
 */

public class Metadata {

    @SerializedName("width")
    private Integer width;

    @SerializedName("url")
    private String url;

    @SerializedName("height")
    private Integer height;



    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

}
