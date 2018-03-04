package com.nytmp.vipin.nytimesmostpopulars;

import android.test.suitebuilder.annotation.SmallTest;

import com.nytmp.vipin.nytimesmostpopulars.data.ArticleSearchFilter;
import com.nytmp.vipin.nytimesmostpopulars.data.DataRepository;
import com.nytmp.vipin.nytimesmostpopulars.data.IDataModel;
import com.nytmp.vipin.nytimesmostpopulars.data.model.Article;
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

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;
import rx.subjects.BehaviorSubject;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Vipin Vasu on 21/02/18.
 */

@SmallTest
@RunWith(JUnit4.class)
public class ArticlesViewModelTest {

    @Mock
    private IDataModel mDataModel;

    private NYTimesQueryService mRemoteService;

    ArticlesViewModel articlesViewModel;

    private ISchedulerProvider mSchedulerProvider;
    private DataRepository dataRepository;
    private TestSubscriber<List<ArticleView>> articleViewTestSubscriber;
    private TestSubscriber<Boolean> testSubscriberIsLoadingSubject;
    private TestSubscriber mCompletableSubscriber;

    private static List<Article> ARTICLES;





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
        articlesViewModel = new ArticlesViewModel(mSchedulerProvider,mDataModel);
        articleViewTestSubscriber = new TestSubscriber<>();
        testSubscriberIsLoadingSubject = new TestSubscriber<>();
        mCompletableSubscriber = new TestSubscriber<>();
        ARTICLES = createFakeList();
    }

    @Test
    public void testGetArticleData()
    {
        withTasksInRepositoryAndSubscribed(ARTICLES);
    }

    @Test
    public void testGetArticleDataPreload()
    {
        withTasksInRepositoryAndSubscribedPreload(ARTICLES);
    }

    private void withTasksInRepositoryAndSubscribed(List<Article> articleList){
        // Given that the task repository returns tasks
        DataRepository mock = org.mockito.Mockito.mock(DataRepository.class);
        when(mock.searchArticles(new ArticleSearchFilter())).thenReturn(Observable.just(articleList));
        // Given that we are subscribed to the tasks
        articlesViewModel.searchArticles(null, true).subscribe(articleViewTestSubscriber);
    }

    private void withTasksInRepositoryAndSubscribedPreload(List<Article> articleList){
        // Given that the task repository returns tasks
        DataRepository mock = org.mockito.Mockito.mock(DataRepository.class);
        when(mock.searchArticles(new ArticleSearchFilter())).thenReturn(Observable.just(articleList));
        // Given that we are subscribed to the tasks
        articlesViewModel.preloadArticles(1).subscribe(articleViewTestSubscriber);
    }


    private List<Article> createFakeList()
    {
        List<Article> results = new ArrayList<>();
        Article result = new Article();
        result.setHeadline("Title 1");
        result.setLeadParagraph("sample data");
        return results;
    }
}

