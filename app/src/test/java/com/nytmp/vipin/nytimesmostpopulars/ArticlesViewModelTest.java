package com.nytmp.vipin.nytimesmostpopulars;

import com.nytmp.vipin.nytimesmostpopulars.data.DataRepository;
import com.nytmp.vipin.nytimesmostpopulars.data.IDataModel;
import com.nytmp.vipin.nytimesmostpopulars.data.remote.NYTimesSearchService;
import com.nytmp.vipin.nytimesmostpopulars.data.remote.RetrofitNYTimesSearchService;
import com.nytmp.vipin.nytimesmostpopulars.schedulers.ISchedulerProvider;
import com.nytmp.vipin.nytimesmostpopulars.schedulers.SchedulerProvider;
import com.nytmp.vipin.nytimesmostpopulars.viewmodel.ArticlesViewModel;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;

/**
 * Created by Vipin Vasu on 21/02/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class ArticlesViewModelTest {

    @Mock
    private IDataModel mDataModel;
    ArticlesViewModel articlesViewModel;
    private ISchedulerProvider mSchedulerProvider;
    private ArticlesViewModel mViewModel;
    private NYTimesSearchService mRemoteService;

    private DataRepository dataRepository;



    @BeforeClass
    public static void onlyOnce() throws Exception {
    }

    @Before
    public void setUp() throws Exception {

        mRemoteService = mock(NYTimesSearchService.class);
        mDataModel = com.nytmp.vipin.nytimesmostpopulars.data.DataRepository.getInstance(RetrofitNYTimesSearchService.newNYTSearchService());
        mSchedulerProvider = SchedulerProvider.getInstance();
        dataRepository = DataRepository.getInstance(mRemoteService);

    }

    @Test
    public void testGetArticleData()
    {
        articlesViewModel = new ArticlesViewModel(mSchedulerProvider, mDataModel);
        dataRepository.searchArticles(articlesViewModel.getFilter());

    }
}
