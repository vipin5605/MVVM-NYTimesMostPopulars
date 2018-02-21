package com.nytmp.vipin.nytimesmostpopulars.util;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by Vipin Vasu on 21/02/18.
 */

public class NetworkUtil {
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }
}
