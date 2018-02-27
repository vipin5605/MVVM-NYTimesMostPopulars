package com.nytmp.vipin.nytimesmostpopulars.databinding;
import com.nytmp.vipin.nytimesmostpopulars.R;
import com.nytmp.vipin.nytimesmostpopulars.BR;
import android.view.View;
public class ItemArticleBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    private final android.support.v7.widget.CardView mboundView0;
    private final android.widget.TextView mboundView4;
    public final android.widget.TextView tvDescription;
    public final android.widget.TextView tvTitle;
    public final android.widget.TextView txPubDate;
    // variables
    private com.nytmp.vipin.nytimesmostpopulars.data.model.ArticleView mArticle;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemArticleBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds);
        this.mboundView0 = (android.support.v7.widget.CardView) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView4 = (android.widget.TextView) bindings[4];
        this.mboundView4.setTag(null);
        this.tvDescription = (android.widget.TextView) bindings[2];
        this.tvDescription.setTag(null);
        this.tvTitle = (android.widget.TextView) bindings[1];
        this.tvTitle.setTag(null);
        this.txPubDate = (android.widget.TextView) bindings[3];
        this.txPubDate.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    public boolean setVariable(int variableId, Object variable) {
        switch(variableId) {
            case BR.article :
                setArticle((com.nytmp.vipin.nytimesmostpopulars.data.model.ArticleView) variable);
                return true;
        }
        return false;
    }

    public void setArticle(com.nytmp.vipin.nytimesmostpopulars.data.model.ArticleView Article) {
        this.mArticle = Article;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.article);
        super.requestRebind();
    }
    public com.nytmp.vipin.nytimesmostpopulars.data.model.ArticleView getArticle() {
        return mArticle;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        boolean articleHasNewsDesk = false;
        java.lang.String articleTitle = null;
        java.lang.String articleNewsDesk = null;
        int articleHasNewsDeskViewVISIBLEViewGONE = 0;
        java.lang.String articleDescription = null;
        com.nytmp.vipin.nytimesmostpopulars.data.model.ArticleView article = mArticle;
        java.lang.String articleDate = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (article != null) {
                    // read article.hasNewsDesk
                    articleHasNewsDesk = article.hasNewsDesk();
                    // read article.title
                    articleTitle = article.getTitle();
                    // read article.newsDesk
                    articleNewsDesk = article.getNewsDesk();
                    // read article.description
                    articleDescription = article.getDescription();
                    // read article.date
                    articleDate = article.getDate();
                }
            if((dirtyFlags & 0x3L) != 0) {
                if(articleHasNewsDesk) {
                        dirtyFlags |= 0x8L;
                }
                else {
                        dirtyFlags |= 0x4L;
                }
            }


                // read article.hasNewsDesk ? View.VISIBLE : View.GONE
                articleHasNewsDeskViewVISIBLEViewGONE = ((articleHasNewsDesk) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView4, articleNewsDesk);
            this.mboundView4.setVisibility(articleHasNewsDeskViewVISIBLEViewGONE);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvDescription, articleDescription);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvTitle, articleTitle);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.txPubDate, articleDate);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    public static ItemArticleBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ItemArticleBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ItemArticleBinding>inflate(inflater, com.nytmp.vipin.nytimesmostpopulars.R.layout.item_article, root, attachToRoot, bindingComponent);
    }
    public static ItemArticleBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ItemArticleBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.nytmp.vipin.nytimesmostpopulars.R.layout.item_article, null, false), bindingComponent);
    }
    public static ItemArticleBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ItemArticleBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/item_article_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ItemArticleBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): article
        flag 1 (0x2L): null
        flag 2 (0x3L): article.hasNewsDesk ? View.VISIBLE : View.GONE
        flag 3 (0x4L): article.hasNewsDesk ? View.VISIBLE : View.GONE
    flag mapping end*/
    //end
}