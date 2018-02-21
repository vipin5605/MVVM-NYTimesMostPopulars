package com.nytmp.vipin.nytimesmostpopulars.data.remote;

import com.google.gson.annotations.SerializedName;
import com.nytmp.vipin.nytimesmostpopulars.data.model.Article;

import java.util.List;

/**
 * Created by Vipin Vasu on 21/02/18.
 */

public class RemoteObject {

    @SerializedName("results")
    public List<Article> articles;

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
