package com.nytmp.vipin.nytimesmostpopulars.data;

import android.support.annotation.NonNull;

import com.nytmp.vipin.nytimesmostpopulars.data.model.Article;

import java.util.List;

import rx.Observable;

/**
 * Created by Vipin Vasu on 21/02/18.
 */

public interface IDataModel {

    @NonNull
    Observable<List<Article>> searchArticles(ArticleSearchFilter filter);

}
