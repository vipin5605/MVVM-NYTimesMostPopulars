package com.nytmp.vipin.nytimesmostpopulars.articlesview;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.nytmp.vipin.nytimesmostpopulars.NyTimesSearchApplication;
import com.nytmp.vipin.nytimesmostpopulars.R;
import com.nytmp.vipin.nytimesmostpopulars.data.model.ArticleView;
import com.nytmp.vipin.nytimesmostpopulars.util.NetworkUtil;
import com.nytmp.vipin.nytimesmostpopulars.viewmodel.ArticlesViewModel;
import com.nytmp.vipin.nytimesmostpopulars.viewutil.EndlessRecyclerViewScrollListener;
import com.nytmp.vipin.nytimesmostpopulars.viewutil.ItemOffsetDecoration;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Vipin Vasu on 21/02/18.
 */

public class MostPopularArticlesActivity extends AppCompatActivity implements ArticleAdapter.OnArticleClickListener {

    private static final String LOG_TAG = "MostPopularArticlesActivity";

    @NonNull
    private CompositeSubscription mSubscriptions;

    @NonNull
    private ArticlesViewModel mViewModel;

    private RecyclerView mRecyclerView;

    private ArticleAdapter mAdapter;
    private EndlessRecyclerViewScrollListener mEndlessScrollListener;

    private MenuItem loadingMenuItem;
    private MenuItem searchMenuItem;
    private MenuItem settingsMenuItem;

    private RelativeLayout layoutEmptySearch;
    private RelativeLayout layoutOffline;
    private RelativeLayout layoutContentSearch;

    private Handler uiHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);
        mRecyclerView = (RecyclerView) findViewById(R.id.rvArticles);

        layoutEmptySearch = (RelativeLayout) findViewById(R.id.layout_empty_search);
        layoutOffline = (RelativeLayout) findViewById(R.id.layout_offline);
        layoutContentSearch = (RelativeLayout) findViewById(R.id.rlContentSearch);

        setupToolbar();
        setupRecyclerView();

        mViewModel = getViewModel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!NetworkUtil.isNetworkAvailable(this)) {
            showHideOfflineLayout(true);
        }
        else {
            showHideOfflineLayout(false);
            initBindings();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbind();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        loadingMenuItem = menu.findItem(R.id.action_loading);
        searchMenuItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchMenuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                prepareNewSearch();
                mSubscriptions.add(mViewModel.searchArticles(query, true).subscribe(list -> {
                }, error -> Log.e(LOG_TAG, error.toString())));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                //do nothing
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                prepareNewSearch();
                mSubscriptions.add(mViewModel.searchArticles(null, true).subscribe(list -> {
                }, error -> Log.e(LOG_TAG, error.toString())));
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void initBindings() {
        Log.d(LOG_TAG, "initBindings()");

        mSubscriptions = new CompositeSubscription();

        mSubscriptions.add(mViewModel.searchResults()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setData, error -> Log.e(LOG_TAG, error.toString())));

        mSubscriptions.add(mViewModel.searchPreload()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::addData, error -> Log.e(LOG_TAG, error.toString())));

        mSubscriptions.add(mViewModel.isLoading()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::dataIsLoading, error -> Log.e(LOG_TAG, error.toString())));

        initSearch(true);
    }

    private void initSearch(boolean firstLoad) {
        Log.d(LOG_TAG, "initSearch()");
        if (!firstLoad) {
            prepareNewSearch();
        }
        mSubscriptions.add(mViewModel.initSearch(false)
                .subscribe(list -> {
                }, error -> {
                    Log.e(LOG_TAG, error.toString());
                    initSearch(firstLoad);
                }));
    }

    private void preloadArticles(final int page) {
        Log.d(LOG_TAG, "preloadArticles() page: " + page);
        mSubscriptions.add(mViewModel.preloadArticles(page)
                .subscribe(list -> {
                }, error -> {
                    Log.e(LOG_TAG, error.toString());
                    uiHandler.postDelayed(() -> preloadArticles(page), 100);
                }));
    }

    private void unbind() {
        if (mSubscriptions != null) {
            mSubscriptions.unsubscribe();
        }
    }

    private ArticlesViewModel getViewModel() {
        return ((NyTimesSearchApplication) getApplication()).getArticlesViewModel();
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setupRecyclerView() {
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.addItemDecoration(new ItemOffsetDecoration(getResources().getDimensionPixelSize(R.dimen.margin_small)));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new ArticleAdapter();
        mAdapter.setArticleClickListener(this);
        mRecyclerView.setAdapter(mAdapter);

        mEndlessScrollListener = new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                preloadArticles(page);
            }
        };

        mRecyclerView.addOnScrollListener(mEndlessScrollListener);
    }

    private void setData(List<ArticleView> articles) {
        if (articles == null || articles.size() == 0) {
            mRecyclerView.setVisibility(View.GONE);
            layoutEmptySearch.setVisibility(View.VISIBLE);
        }
        else {
            mRecyclerView.setVisibility(View.VISIBLE);
            mAdapter.setArticles(articles);
            layoutEmptySearch.setVisibility(View.GONE);
        }
    }

    private void addData(List<ArticleView> articles) {
        if (articles != null && articles.size() > 0) {
            mAdapter.addArticles(articles);
        }
    }

    private void dataIsLoading(boolean isLoading) {
        Log.d(LOG_TAG, "dataIsLoading(): " + isLoading);
        if (loadingMenuItem != null) loadingMenuItem.setVisible(isLoading);
        if (searchMenuItem != null) searchMenuItem.setVisible(!isLoading);
        if (settingsMenuItem != null) settingsMenuItem.setVisible(!isLoading);
    }

    private void showHideOfflineLayout(boolean isOffline) {
        layoutOffline.setVisibility(isOffline ? View.VISIBLE : View.GONE);
        layoutContentSearch.setVisibility(isOffline ? View.GONE : View.VISIBLE);
    }

    private void prepareNewSearch() {
        Log.d(LOG_TAG, "prepareNewSearch()");
        mAdapter.clearData();
        mEndlessScrollListener.resetState();
    }

    @Override
    public void onArticleClick(ArticleView articleView) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(ContextCompat.getColor(this, R.color.colorAccent));
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(articleView.getWebUrl()));
    }



}
