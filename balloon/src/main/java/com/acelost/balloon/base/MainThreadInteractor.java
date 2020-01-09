package com.acelost.balloon.base;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Preconditions;

public abstract class MainThreadInteractor {

    @NonNull
    private final Object MAIN_HANDLER_GUARD = new Object();

    @Nullable
    private volatile Handler mainHandler;

    @NonNull
    protected Handler getMainHandler() {
        if (mainHandler == null) {
            synchronized (MAIN_HANDLER_GUARD) {
                if (mainHandler == null) {
                    mainHandler = new Handler(Looper.getMainLooper());
                }
            }
        }
        return Preconditions.checkNotNull(mainHandler);
    }

    @AnyThread
    protected void doOnMainThread(@NonNull final Runnable runnable) {
        Preconditions.checkNotNull(runnable);
        if (isMainThread()) {
            runnable.run();
        } else {
            getMainHandler().post(runnable);
        }
    }

    private static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

}
