package com.nytmp.vipin.nytimesmostpopulars.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Vipin Vasu on 21/02/18.
 */

public class ArticleSearchFilter implements Parcelable {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

    public static final String NEWS_DESK_ARTS = "Arts";
    public static final String NEWS_DESK_FASHION = "Fashion & Style";
    public static final String NEWS_DESK_SPORTS = "Sports";

    String query;

    Date beginDate;

    String sortOrder;

    List<String> newsDesks;

    int page;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        if (page < 0) return;
        this.page = page;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void clear() {
        this.page = 0;
        this.query = null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.query);
        dest.writeLong(this.beginDate != null ? this.beginDate.getTime() : -1);
        dest.writeString(this.sortOrder);
        dest.writeStringList(this.newsDesks);
        dest.writeInt(this.page);
    }

    public ArticleSearchFilter() {
        newsDesks = new ArrayList<>();

        newsDesks.add(NEWS_DESK_ARTS);
        newsDesks.add(NEWS_DESK_FASHION);
        newsDesks.add(NEWS_DESK_SPORTS);
    }

    protected ArticleSearchFilter(Parcel in) {
        this.query = in.readString();
    }

    public static final Parcelable.Creator<ArticleSearchFilter> CREATOR = new Parcelable.Creator<ArticleSearchFilter>() {
        @Override
        public ArticleSearchFilter createFromParcel(Parcel source) {
            return new ArticleSearchFilter(source);
        }

        @Override
        public ArticleSearchFilter[] newArray(int size) {
            return new ArticleSearchFilter[size];
        }
    };

    @Override
    public String toString() {
        return query;
    }
}

