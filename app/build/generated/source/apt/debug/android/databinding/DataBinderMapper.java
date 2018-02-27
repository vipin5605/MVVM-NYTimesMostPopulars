
package android.databinding;
import com.nytmp.vipin.nytimesmostpopulars.BR;
class DataBinderMapper  {
    final static int TARGET_MIN_SDK = 25;
    public DataBinderMapper() {
    }
    public android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View view, int layoutId) {
        switch(layoutId) {
                case com.nytmp.vipin.nytimesmostpopulars.R.layout.item_article:
                    return com.nytmp.vipin.nytimesmostpopulars.databinding.ItemArticleBinding.bind(view, bindingComponent);
                case com.nytmp.vipin.nytimesmostpopulars.R.layout.item_media_article:
                    return com.nytmp.vipin.nytimesmostpopulars.databinding.ItemMediaArticleBinding.bind(view, bindingComponent);
        }
        return null;
    }
    android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View[] views, int layoutId) {
        switch(layoutId) {
        }
        return null;
    }
    int getLayoutId(String tag) {
        if (tag == null) {
            return 0;
        }
        final int code = tag.hashCode();
        switch(code) {
            case 118356608: {
                if(tag.equals("layout/item_article_0")) {
                    return com.nytmp.vipin.nytimesmostpopulars.R.layout.item_article;
                }
                break;
            }
            case -2127023835: {
                if(tag.equals("layout/item_media_article_0")) {
                    return com.nytmp.vipin.nytimesmostpopulars.R.layout.item_media_article;
                }
                break;
            }
        }
        return 0;
    }
    String convertBrIdToString(int id) {
        if (id < 0 || id >= InnerBrLookup.sKeys.length) {
            return null;
        }
        return InnerBrLookup.sKeys[id];
    }
    private static class InnerBrLookup {
        static String[] sKeys = new String[]{
            "_all"
            ,"article"
            ,"mediaArticle"};
    }
}