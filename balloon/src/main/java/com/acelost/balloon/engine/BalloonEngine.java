package com.acelost.balloon.engine;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;

import com.acelost.balloon.base.ActivityLifecycleListener;
import com.acelost.balloon.ui.BalloonActionListener;
import com.acelost.balloon.ui.BalloonModel;

import java.lang.ref.WeakReference;
import java.util.List;

import androidx.annotation.AnyThread;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Preconditions;

public final class BalloonEngine {

    @Nullable
    private static ActivityObserver activityObserver;

    @Nullable
    private static WeakReference<Application> applicationRef;

    @NonNull
    private static final BalloonCollectionCallbacks collectionCallbacks = new BalloonCollectionCallbacks() {
        @Override
        public void onCollectionChanged() {
            notifyCollectionChanged();
        }
    };

    @NonNull
    private static final BalloonActionListener balloonActionListener = new BalloonActionListener() {
        @Override
        public void onCancelBalloonClick(@NonNull final BalloonModel model) {
            collectionController.remove(model);
        }
    };

    @NonNull
    private static final BalloonCollectionController collectionController = new BalloonCollectionController(collectionCallbacks);

    @NonNull
    private static final BalloonViewController viewController = new BalloonViewController(balloonActionListener);

    @MainThread
    public static void launch(@NonNull final Application application) {
        Preconditions.checkNotNull(application);
        final Application current = applicationRef != null ? applicationRef.get() : null;
        if (current != application) {
            if (current != null) {
                current.unregisterActivityLifecycleCallbacks(activityObserver);
            }
            if (activityObserver == null) {
                activityObserver = new ActivityObserver();
            }
            applicationRef = new WeakReference<>(application);
            application.registerActivityLifecycleCallbacks(activityObserver);
        }
    }

    @SuppressLint("WrongThread")
    @AnyThread
    public static void show(@NonNull final BalloonModel balloon, final long duration) {
        Preconditions.checkNotNull(balloon);
        collectionController.add(balloon, duration);
    }

    @SuppressLint("WrongThread")
    @AnyThread
    public static void dismiss(@NonNull final BalloonModel balloon) {
        Preconditions.checkNotNull(balloon);
        collectionController.remove(balloon);
    }

    @SuppressLint("WrongThread")
    @AnyThread
    public static void dismissById(@NonNull final String id) {
        Preconditions.checkNotNull(id);
        collectionController.remove(id);
    }

    @SuppressWarnings("WrongThread")
    @AnyThread
    public static void dismissAll() {
        collectionController.removeAll();
    }

    @MainThread
    private static void notifyCollectionChanged() {
        final List<BalloonModel> snapshot = collectionController.createSnapshot();
        viewController.showBalloonsSnapshot(snapshot);
    }

    @MainThread
    private static void attachToActivity(@NonNull final Activity activity) {
        Preconditions.checkNotNull(activity);
        viewController.attachToActivity(activity);
    }

    @MainThread
    private static void detachFromActivity(@NonNull final Activity activity) {
        Preconditions.checkNotNull(activity);
        viewController.detachFromActivity(activity);
    }

    private static class ActivityObserver extends ActivityLifecycleListener {

        @Override
        public void onActivityResumed(final Activity activity) {
            attachToActivity(activity);
        }

        @Override
        public void onActivityPaused(final Activity activity) {
            detachFromActivity(activity);
        }
    }

}
