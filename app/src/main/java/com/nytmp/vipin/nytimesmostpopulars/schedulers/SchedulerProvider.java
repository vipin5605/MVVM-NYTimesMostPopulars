package com.nytmp.vipin.nytimesmostpopulars.schedulers;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Vipin Vasu on 21/02/18.
 */

public class SchedulerProvider implements ISchedulerProvider {

    @Nullable
    private static SchedulerProvider INSTANCE;

    // Prevent direct instantiation.
    private SchedulerProvider() {
    }

    public static SchedulerProvider getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SchedulerProvider();
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public Scheduler computation() {
        return Schedulers.computation();
    }

    @NonNull
    @Override
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }
}

