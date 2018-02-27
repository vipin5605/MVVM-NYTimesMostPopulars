package com.nytmp.vipin.nytimesmostpopulars.articlesview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.bumptech.glide.Glide;
import com.nytmp.vipin.nytimesmostpopulars.R;
import com.nytmp.vipin.nytimesmostpopulars.data.model.ArticleView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vipin Vasu on 21/02/18.
 */

public class ArticleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private List<ArticleView> articles = new ArrayList<>();
    private List<ArticleView> articlesCopy = new ArrayList<>();
    private static final int ARTICLE_ITEM_TYPE = 0;
    private static final int MEDIA_ARTICLE_ITEM_TYPE = 1;

    private OnArticleClickListener mArticleClickListener;
    ArticlesFilter articlesFilter;

    @Override
    public Filter getFilter() {
        if (articlesFilter == null) {
            articlesFilter = new ArticlesFilter();
        }
        return articlesFilter;
    }

    public interface OnArticleClickListener {
        void onArticleClick(ArticleView articleView);
    }

    public void setArticleClickListener(OnArticleClickListener mArticleClickListener) {
        this.mArticleClickListener = mArticleClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == MEDIA_ARTICLE_ITEM_TYPE) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_media_article, parent, false);
            return new MediaArticleViewHolder(v);
        }
        else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false);
            return new ArticleViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ArticleView article = articles.get(position);

        if (getItemViewType(position) == MEDIA_ARTICLE_ITEM_TYPE) {
            ((MediaArticleViewHolder) holder).onBind(article, mArticleClickListener);
        }
        else {
            ((ArticleViewHolder) holder).onBind(article, mArticleClickListener);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (articles.get(position).isMedia()) {
            return MEDIA_ARTICLE_ITEM_TYPE;
        }
        else return ARTICLE_ITEM_TYPE;
    }

    @Override
    public int getItemCount() {
        return articles == null ? 0 : articles.size();
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
        if (holder instanceof MediaArticleViewHolder) {
            Glide.clear(((MediaArticleViewHolder) holder).getImageView());
        }
    }

    public void setArticles(List<ArticleView> newArticles) {
        articles = newArticles == null ? new ArrayList<>() : new ArrayList<>(newArticles);
        articlesCopy = newArticles == null ? new ArrayList<>() : new ArrayList<>(newArticles);
        notifyDataSetChanged();
    }

    public void addArticles(List<ArticleView> newArticles) {
        if (newArticles == null || newArticles.size() == 0) return;
        if (articles != null && articles.size() > 0) {
            int startPosition = getItemCount();
            articles.addAll(newArticles);
            articlesCopy.addAll(newArticles);
            notifyItemRangeInserted(startPosition, newArticles.size());
        }
        else {
            articles = new ArrayList<>(newArticles);
            articlesCopy = new ArrayList<>(newArticles);
            notifyDataSetChanged();
        }
    }

    public void clearData() {
        articles.clear();
        articlesCopy.clear();
        notifyDataSetChanged();
    }

    private class ArticlesFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                List filterList = new ArrayList();
                for (int i = 0; i < articlesCopy.size(); i++) {
                    if ((articlesCopy.get(i).getTitle().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(articlesCopy.get(i));
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = articlesCopy.size();
                results.values = articlesCopy;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            articles = (List) results.values;
            notifyDataSetChanged();
        }

    }
}
