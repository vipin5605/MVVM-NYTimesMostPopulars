package com.nytmp.vipin.nytimesmostpopulars.data.remote;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Vipin Vasu on 21/02/18.
 */

public interface NYTimesSearchService {

    //String ENDPOINT = "https://api.nytimes.com/svc/search/v2/";
    String ENDPOINT = "http://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/7/";
    //http://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/7/?api-key=9a4a2c2f34d7467c93f5d8f13e9ce202

    @GET("articlesearch.json")
    Observable<RemoteObject> searchArticles(@Query("api-key") String apiKey);
}
