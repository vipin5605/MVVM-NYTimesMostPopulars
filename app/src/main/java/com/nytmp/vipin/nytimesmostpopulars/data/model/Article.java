package com.nytmp.vipin.nytimesmostpopulars.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by Vipin Vasu on 21/02/18.
 */

public class Article {

    @SerializedName("url")
    String webUrl;

    @SerializedName("title")
    String headline;

    @SerializedName("abstract")
    String leadParagraph;

    private List<ArticleMedia> multimedia = null;


    @SerializedName("published_date")
    private Date publicationDate;

    public String getWebUrl() {
        return webUrl;
    }

    public String getLeadParagraph() {
        return leadParagraph;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public void setLeadParagraph(String leadParagraph) {
        this.leadParagraph = leadParagraph;
    }

    public List<ArticleMedia> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<ArticleMedia> multimedia) {
        this.multimedia = multimedia;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }


    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }
}
