package com.nytmp.vipin.nytimesmostpopulars.viewmodel;

import android.support.annotation.NonNull;
import android.util.Log;

import com.nytmp.vipin.nytimesmostpopulars.data.ArticleSearchFilter;
import com.nytmp.vipin.nytimesmostpopulars.data.IDataModel;
import com.nytmp.vipin.nytimesmostpopulars.data.model.ArticleView;
import com.nytmp.vipin.nytimesmostpopulars.schedulers.ISchedulerProvider;

import java.util.Date;
import java.util.List;

import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Created by Vipin Vasu on 21/02/18.
 */

public class ArticlesViewModel {


    private static final String LOG_TAG = "ArticlesViewModel";

    @NonNull
    private final ISchedulerProvider mSchedulerProvider;

    @NonNull
    private final IDataModel mDataModel;

    private final BehaviorSubject<List<ArticleView>> mSearchResultsSubject = BehaviorSubject.create();
    private final BehaviorSubject<List<ArticleView>> mSearchPreloadSubject = BehaviorSubject.create();
    private BehaviorSubject<Boolean> isLoadingSubject = BehaviorSubject.create(false);

    private ArticleSearchFilter filter;

    public ArticlesViewModel(@NonNull ISchedulerProvider mSchedulerProvider, @NonNull IDataModel mDataModel) {
        this.mSchedulerProvider = mSchedulerProvider;
        this.mDataModel = mDataModel;
        this.filter = new ArticleSearchFilter();
    }

    public final Observable<List<ArticleView>> searchResults() {
        return mSearchResultsSubject.asObservable();
    }

    public final Observable<List<ArticleView>> searchPreload() {
        return mSearchPreloadSubject.asObservable();
    }

    public final Observable<Boolean> isLoading() {
        return isLoadingSubject.asObservable();
    }


    public Observable<List<ArticleView>> initSearch(boolean updateQuery) {
        Log.d(LOG_TAG, "initSearch()");
        return searchArticles(null, updateQuery);
    }

    public Observable<List<ArticleView>> searchArticles(String query, boolean updateQuery) {
        Log.d(LOG_TAG, "searchArticles()");
        isLoadingSubject.onNext(true);

        if (updateQuery) filter.setQuery(query);

        return mDataModel.searchArticles(filter)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .flatMapIterable(list -> list)
                .map(ArticleView::new)
                .toList()
                .doOnNext(list -> {
                    Log.d(LOG_TAG, "searchArticles: onNext(), list size: " + list.size());
                    mSearchResultsSubject.onNext(list);
                })
                .doOnError(error -> {
                    Log.e(LOG_TAG, error.toString());
                    isLoadingSubject.onNext(false);
                })
                .doOnTerminate(() -> isLoadingSubject.onNext(false));
    }

    public Observable<List<ArticleView>> preloadArticles(int page) {
        Log.d(LOG_TAG, "loadMoreArticles page: " + page);

        return mDataModel.searchArticles(filter)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .flatMapIterable(list -> list)
                .map(ArticleView::new)
                .toList()
                .doOnNext(list -> {
                    Log.d(LOG_TAG, "it was additionally loaded: " + (list == null ? 0 : list.size()));
                    mSearchPreloadSubject.onNext(list);
                })
                .doOnError(error -> Log.e(LOG_TAG, error.toString()));
    }

    public ArticleSearchFilter getFilter() {
        return filter;
    }

}
