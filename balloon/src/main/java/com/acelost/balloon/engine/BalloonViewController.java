package com.acelost.balloon.engine;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acelost.balloon.R;
import com.acelost.balloon.ui.BalloonAdapter;
import com.acelost.balloon.ui.BalloonActionListener;
import com.acelost.balloon.ui.BalloonItemAnimator;
import com.acelost.balloon.ui.BalloonModel;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Preconditions;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

final class BalloonViewController {

    @NonNull
    private final Map<Activity, BalloonAdapter> adapters = new HashMap<>();

    @NonNull
    private final BalloonActionListener listener;

    @Nullable
    private List<BalloonModel> snapshot;

    BalloonViewController(@NonNull final BalloonActionListener listener) {
        this.listener = Preconditions.checkNotNull(listener);
    }

    @MainThread
    void attachToActivity(@NonNull final Activity activity) {
        Preconditions.checkNotNull(activity);
        final View decorView = activity.getWindow().getDecorView();
        if (decorView instanceof ViewGroup) {
            final RecyclerView balloonList = findOrInflateBalloonList((ViewGroup) decorView);
            balloonList.setItemAnimator(new BalloonItemAnimator());
            final BalloonAdapter adapter = getAdapter(balloonList);
            adapters.put(activity, adapter);
            // Submit current list
            submitBalloonsSnapshot(adapter);
        }
    }

    @MainThread
    void detachFromActivity(@NonNull final Activity activity) {
        Preconditions.checkNotNull(activity);
        adapters.remove(activity);
    }

    @MainThread
    void showBalloonsSnapshot(@NonNull final List<BalloonModel> snapshot) {
        Preconditions.checkNotNull(snapshot);
        this.snapshot = snapshot;
        for (BalloonAdapter adapter : adapters.values()) {
            submitBalloonsSnapshot(adapter);
        }
    }

    @MainThread
    private void submitBalloonsSnapshot(@NonNull final BalloonAdapter adapter) {
        if (snapshot != null) {
            adapter.submitList(snapshot);
        } else {
            adapter.submitList(Collections.emptyList());
        }
    }

    @NonNull
    private RecyclerView findOrInflateBalloonList(@NonNull final ViewGroup container) {
        final RecyclerView reusableView = container.findViewById(R.id.balloon_list);
        if (reusableView != null) {
            return reusableView;
        }
        final Context context = container.getContext();
        final ViewGroup balloonLayout = (ViewGroup) LayoutInflater.from(context)
                .inflate(R.layout.layout_balloon_list, container, false);
        final RecyclerView balloonList = balloonLayout.findViewById(R.id.balloon_list);
        balloonList.setLayoutManager(new LinearLayoutManager(context));
        balloonList.setAdapter(new BalloonAdapter(listener));
        final ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        );
        lp.topMargin = getStatusBarHeight(context) + getListItemMargin(context);
        container.addView(balloonLayout, -1, lp);
        return balloonList;
    }

    private static int getStatusBarHeight(@NonNull final Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return context.getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    private static int getListItemMargin(@NonNull final Context context) {
        return context.getResources().getDimensionPixelSize(R.dimen.balloon_list_item_margin);
    }

    @NonNull
    private static BalloonAdapter getAdapter(@NonNull final RecyclerView recyclerView) {
        final RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter == null) {
            throw new IllegalStateException("Adapter for recycler view is not specified.");
        } else if (adapter instanceof BalloonAdapter) {
            return (BalloonAdapter) adapter;
        } else {
            throw new IllegalStateException("Adapter for recycler view has invalid type: " + adapter.getClass().getCanonicalName());
        }
    }

}
