package com.nytmp.vipin.nytimesmostpopulars.schedulers;

import android.support.annotation.NonNull;

import rx.Scheduler;

/**
 * Created by Vipin Vasu on 21/02/18.
 */

public interface ISchedulerProvider {
    @NonNull
    Scheduler computation();

    @NonNull
    Scheduler ui();
}
