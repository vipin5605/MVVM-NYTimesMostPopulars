package com.nytmp.vipin.nytimesmostpopulars.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Vipin Vasu on 21/02/18.
 */

public class ArticleMedia {


    @SerializedName("subtype")
    private String subtype;

    @SerializedName("type")
    private String type;

    public List<Metadata> getMetadata() {
        return metadata;
    }

    public void setMetadata(List<Metadata> metadata) {
        this.metadata = metadata;
    }

    @SerializedName("media-metadata")
    private List<Metadata> metadata = null;


    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
