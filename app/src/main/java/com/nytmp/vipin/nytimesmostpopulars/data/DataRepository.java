package com.nytmp.vipin.nytimesmostpopulars.data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.nytmp.vipin.nytimesmostpopulars.data.model.Article;
import com.nytmp.vipin.nytimesmostpopulars.data.remote.NYTimesSearchService;

import java.util.List;

import rx.Observable;

/**
 * Created by Vipin Vasu on 21/02/18.
 */

public class DataRepository implements IDataModel {

    private static final String LOG_TAG = "DataRepository";

    private static DataRepository instance = null;

    private NYTimesSearchService mRemoteService;

    private static final String API_KEY = "9a4a2c2f34d7467c93f5d8f13e9ce202";



    private DataRepository(NYTimesSearchService nyTimesSearchService) {
        this.mRemoteService = nyTimesSearchService;
    }

    public static DataRepository getInstance(NYTimesSearchService nyTimesSearchService) {
        if (instance == null) {
            instance = new DataRepository(nyTimesSearchService);
        }
        return instance;
    }

    @NonNull
    @Override
    public Observable<List<Article>> searchArticles(ArticleSearchFilter filter) {
        Log.d(LOG_TAG, "Try to search articles...filter:" + filter.toString());

        return mRemoteService.searchArticles(API_KEY)
                .map(remoteObject -> remoteObject.getArticles())
                .doOnError(error -> Log.e(LOG_TAG, error.toString()));
    }
}
