package com.nytmp.vipin.nytimesmostpopulars;

import android.app.Application;

import com.nytmp.vipin.nytimesmostpopulars.data.DataRepository;
import com.nytmp.vipin.nytimesmostpopulars.data.IDataModel;
import com.nytmp.vipin.nytimesmostpopulars.data.remote.RetrofitNYTimesSearchService;
import com.nytmp.vipin.nytimesmostpopulars.schedulers.ISchedulerProvider;
import com.nytmp.vipin.nytimesmostpopulars.schedulers.SchedulerProvider;
import com.nytmp.vipin.nytimesmostpopulars.viewmodel.ArticlesViewModel;

/**
 * Created by Vipin Vasu on 21/02/18.
 */

public class NyTimesSearchApplication extends Application {

    private IDataModel mDataModel;

    public NyTimesSearchApplication() {
        mDataModel = DataRepository.getInstance(RetrofitNYTimesSearchService.newNYTSearchService());
    }

    public IDataModel getDataModel() {
        return mDataModel;
    }

    public ISchedulerProvider getScheduler() {
        return SchedulerProvider.getInstance();
    }

    public ArticlesViewModel getArticlesViewModel() {
        return new ArticlesViewModel(getScheduler(), getDataModel());
    }
}

