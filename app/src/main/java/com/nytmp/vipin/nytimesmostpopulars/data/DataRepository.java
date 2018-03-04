package com.nytmp.vipin.nytimesmostpopulars.data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.nytmp.vipin.nytimesmostpopulars.data.model.Article;
import com.nytmp.vipin.nytimesmostpopulars.data.remote.NYTimesQueryService;

import java.util.List;

import rx.Observable;

/**
 * Created by Vipin Vasu on 21/02/18.
 */

public class DataRepository implements IDataModel {

    private static final String LOG_TAG = "DataRepository";

    private static DataRepository instance = null;

    private NYTimesQueryService mRemoteService;

    private static final String API_KEY = "9a4a2c2f34d7467c93f5d8f13e9ce202";



    private DataRepository(NYTimesQueryService nyTimesQueryService) {
        this.mRemoteService = nyTimesQueryService;
    }

    public static DataRepository getInstance(NYTimesQueryService nyTimesQueryService) {
        if (instance == null) {
            instance = new DataRepository(nyTimesQueryService);
        }
        return instance;
    }

    @NonNull
    @Override
    public Observable<List<Article>> searchArticles(ArticleSearchFilter filter) {

        return mRemoteService.searchArticles(API_KEY)
                .map(remoteObject -> remoteObject.getArticles())
                .doOnError(error -> Log.e(LOG_TAG, error.toString()));
    }
}
