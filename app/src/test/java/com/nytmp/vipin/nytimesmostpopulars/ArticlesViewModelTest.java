package com.nytmp.vipin.nytimesmostpopulars;

import android.test.suitebuilder.annotation.SmallTest;

import com.nytmp.vipin.nytimesmostpopulars.data.DataRepository;
import com.nytmp.vipin.nytimesmostpopulars.data.IDataModel;
import com.nytmp.vipin.nytimesmostpopulars.data.model.ArticleView;
import com.nytmp.vipin.nytimesmostpopulars.data.remote.NYTimesQueryService;
import com.nytmp.vipin.nytimesmostpopulars.data.remote.RetrofitNYTimesSearchService;
import com.nytmp.vipin.nytimesmostpopulars.schedulers.ISchedulerProvider;
import com.nytmp.vipin.nytimesmostpopulars.schedulers.SchedulerProvider;
import com.nytmp.vipin.nytimesmostpopulars.viewmodel.ArticlesViewModel;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import rx.observers.TestSubscriber;
import rx.subjects.BehaviorSubject;

import static org.mockito.Mockito.mock;

/**
 * Created by Vipin Vasu on 21/02/18.
 */

@SmallTest
@RunWith(JUnit4.class)
public class ArticlesViewModelTest {

    @Mock
    private IDataModel mDataModel;
    ArticlesViewModel articlesViewModel;
    private ISchedulerProvider mSchedulerProvider;
    private NYTimesQueryService mRemoteService;

    private DataRepository dataRepository;
    private TestSubscriber<ArticleView> articleViewTestSubscriber;
    private TestSubscriber<Boolean> testSubscriberIsLoadingSubject;





    @BeforeClass
    public static void onlyOnce() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mRemoteService = mock(NYTimesQueryService.class);
        mDataModel = com.nytmp.vipin.nytimesmostpopulars.data.DataRepository.getInstance(RetrofitNYTimesSearchService.newNYTSearchService());
        mSchedulerProvider = SchedulerProvider.getInstance();
        dataRepository = DataRepository.getInstance(mRemoteService);
        articleViewTestSubscriber = TestSubscriber.create();
    }

    @Test
    public void testGetArticleData()
    {
        articlesViewModel = new ArticlesViewModel(mSchedulerProvider, mDataModel);
        dataRepository.searchArticles(articlesViewModel.getFilter()).subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .flatMapIterable(list -> list)
                .map(ArticleView::new)
                .toList()
                .doOnNext(list -> {
                    articleViewTestSubscriber.assertReceivedOnNext(list);
                })
                .doOnError(error -> {
                    testSubscriberIsLoadingSubject.onNext(false);
                })
                .doOnTerminate(() -> testSubscriberIsLoadingSubject.onNext(false));


    }
}

