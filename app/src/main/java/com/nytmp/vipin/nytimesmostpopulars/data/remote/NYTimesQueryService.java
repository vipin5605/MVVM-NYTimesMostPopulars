package com.nytmp.vipin.nytimesmostpopulars.data.remote;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Vipin Vasu on 21/02/18.
 */

public interface NYTimesQueryService {

    String ENDPOINT = "http://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/7/";

    @GET("articlesearch.json")
    Observable<RemoteObject> searchArticles(@Query("api-key") String apiKey);
}
