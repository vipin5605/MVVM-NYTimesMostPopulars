package com.nytmp.vipin.nytimesmostpopulars.articlesview;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nytmp.vipin.nytimesmostpopulars.BR;
import com.nytmp.vipin.nytimesmostpopulars.data.model.ArticleView;

/**
 * Created by Vipin Vasu on 21/02/18.
 */

public class ArticleViewHolder extends RecyclerView.ViewHolder {

    private ViewDataBinding binding;

    public ArticleViewHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    public ViewDataBinding getBinding() {
        return binding;
    }

    public void onBind(final ArticleView article, final ArticleAdapter.OnArticleClickListener listener) {
        binding.setVariable(BR.article, article);
        binding.executePendingBindings();
        if (listener != null) {
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onArticleClick(article);
                }
            });
        }
    }
}

