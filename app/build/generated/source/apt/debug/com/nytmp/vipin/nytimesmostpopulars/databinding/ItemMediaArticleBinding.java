package com.nytmp.vipin.nytimesmostpopulars.databinding;
import com.nytmp.vipin.nytimesmostpopulars.R;
import com.nytmp.vipin.nytimesmostpopulars.BR;
import android.view.View;
public class ItemMediaArticleBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.imageView, 5);
    }
    // views
    public final android.widget.ImageView imageView;
    private final android.support.v7.widget.CardView mboundView0;
    private final android.widget.TextView mboundView4;
    public final android.widget.TextView tvDescription;
    public final android.widget.TextView tvTitle;
    public final android.widget.TextView txPubDate;
    // variables
    private com.nytmp.vipin.nytimesmostpopulars.data.model.ArticleView mMediaArticle;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemMediaArticleBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds);
        this.imageView = (android.widget.ImageView) bindings[5];
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
            case BR.mediaArticle :
                setMediaArticle((com.nytmp.vipin.nytimesmostpopulars.data.model.ArticleView) variable);
                return true;
        }
        return false;
    }

    public void setMediaArticle(com.nytmp.vipin.nytimesmostpopulars.data.model.ArticleView MediaArticle) {
        this.mMediaArticle = MediaArticle;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.mediaArticle);
        super.requestRebind();
    }
    public com.nytmp.vipin.nytimesmostpopulars.data.model.ArticleView getMediaArticle() {
        return mMediaArticle;
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
        java.lang.String mediaArticleNewsDesk = null;
        java.lang.String mediaArticleDate = null;
        int mediaArticleHasNewsDeskViewVISIBLEViewGONE = 0;
        boolean mediaArticleHasNewsDesk = false;
        java.lang.String mediaArticleTitle = null;
        com.nytmp.vipin.nytimesmostpopulars.data.model.ArticleView mediaArticle = mMediaArticle;
        java.lang.String mediaArticleDescription = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (mediaArticle != null) {
                    // read mediaArticle.newsDesk
                    mediaArticleNewsDesk = mediaArticle.getNewsDesk();
                    // read mediaArticle.date
                    mediaArticleDate = mediaArticle.getDate();
                    // read mediaArticle.hasNewsDesk
                    mediaArticleHasNewsDesk = mediaArticle.hasNewsDesk();
                    // read mediaArticle.title
                    mediaArticleTitle = mediaArticle.getTitle();
                    // read mediaArticle.description
                    mediaArticleDescription = mediaArticle.getDescription();
                }
            if((dirtyFlags & 0x3L) != 0) {
                if(mediaArticleHasNewsDesk) {
                        dirtyFlags |= 0x8L;
                }
                else {
                        dirtyFlags |= 0x4L;
                }
            }


                // read mediaArticle.hasNewsDesk ? View.VISIBLE : View.GONE
                mediaArticleHasNewsDeskViewVISIBLEViewGONE = ((mediaArticleHasNewsDesk) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView4, mediaArticleNewsDesk);
            this.mboundView4.setVisibility(mediaArticleHasNewsDeskViewVISIBLEViewGONE);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvDescription, mediaArticleDescription);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.tvTitle, mediaArticleTitle);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.txPubDate, mediaArticleDate);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    public static ItemMediaArticleBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ItemMediaArticleBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ItemMediaArticleBinding>inflate(inflater, com.nytmp.vipin.nytimesmostpopulars.R.layout.item_media_article, root, attachToRoot, bindingComponent);
    }
    public static ItemMediaArticleBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ItemMediaArticleBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.nytmp.vipin.nytimesmostpopulars.R.layout.item_media_article, null, false), bindingComponent);
    }
    public static ItemMediaArticleBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ItemMediaArticleBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/item_media_article_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ItemMediaArticleBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): mediaArticle
        flag 1 (0x2L): null
        flag 2 (0x3L): mediaArticle.hasNewsDesk ? View.VISIBLE : View.GONE
        flag 3 (0x4L): mediaArticle.hasNewsDesk ? View.VISIBLE : View.GONE
    flag mapping end*/
    //end
}