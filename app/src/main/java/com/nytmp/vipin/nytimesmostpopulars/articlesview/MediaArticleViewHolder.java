package com.nytmp.vipin.nytimesmostpopulars.articlesview;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nytmp.vipin.nytimesmostpopulars.BR;
import com.nytmp.vipin.nytimesmostpopulars.R;
import com.nytmp.vipin.nytimesmostpopulars.data.model.ArticleView;

/**
 * Created by Vipin Vasu on 21/02/18.
 */

public class MediaArticleViewHolder extends RecyclerView.ViewHolder {

    private ViewDataBinding binding;
    private ImageView imageView;

    public MediaArticleViewHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);
    }

    public ViewDataBinding getBinding() {
        return binding;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void onBind(final ArticleView article, final ArticleAdapter.OnArticleClickListener listener) {
        binding.setVariable(BR.mediaArticle, article);
        binding.executePendingBindings();
        Glide.with(imageView.getContext()).load(article.getFullImageUrl()).into(imageView);
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

