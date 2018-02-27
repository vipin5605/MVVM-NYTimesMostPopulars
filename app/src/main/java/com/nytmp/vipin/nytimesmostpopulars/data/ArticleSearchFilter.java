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


    String query;


    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void clear() {
        this.query = null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.query);
    }

    public ArticleSearchFilter() {

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

