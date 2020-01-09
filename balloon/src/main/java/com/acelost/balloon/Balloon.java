package com.acelost.balloon;

import com.acelost.balloon.action.BalloonAction;
import com.acelost.balloon.engine.BalloonEngine;
import com.acelost.balloon.icon.BalloonIcon;
import com.acelost.balloon.ui.BalloonModel;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Preconditions;

public final class Balloon {

    @Nullable
    private final CharSequence title;

    @NonNull
    private final CharSequence message;

    @Nullable
    private String id;

    private long durationMs;

    @Nullable
    private BalloonIcon icon;

    @Nullable
    private BalloonAction action;

    private boolean cancellable = true;

    private Balloon(@Nullable final CharSequence title, @NonNull final CharSequence message) {
        this.title = title;
        this.message = Preconditions.checkNotNull(message);
    }

    @CheckResult
    @NonNull
    public static Balloon make(@NonNull final CharSequence message) {
        return make(null, message);
    }

    @CheckResult
    @NonNull
    public static Balloon make(@Nullable final CharSequence title, @NonNull final CharSequence message) {
        Preconditions.checkNotNull(message);
        return new Balloon(title, message);
    }

    @CheckResult
    @NonNull
    public Balloon id(@Nullable final String id) {
        this.id = id;
        return this;
    }

    @CheckResult
    @NonNull
    public Balloon duration(final long ms) {
        this.durationMs = ms;
        return this;
    }

    @CheckResult
    @NonNull
    public Balloon icon(@Nullable final BalloonIcon icon) {
        this.icon = icon;
        return this;
    }

    @CheckResult
    @NonNull
    public Balloon action(@NonNull final CharSequence label, @NonNull final Runnable runnable) {
        return action(BalloonAction.fromRunnable(label, runnable));
    }

    @CheckResult
    @NonNull
    public Balloon action(@Nullable final BalloonAction action) {
        this.action = action;
        return this;
    }

    @CheckResult
    @NonNull
    public Balloon cancellable(final boolean cancellable) {
        this.cancellable = cancellable;
        return this;
    }

    public void show() {
        BalloonEngine.show(toModel(this), durationMs);
    }

    public void dismiss() {
        BalloonEngine.dismiss(toModel(this));
    }

    public static void dismiss(@NonNull final String id) {
        Preconditions.checkNotNull(id);
        BalloonEngine.dismissById(id);
    }

    public static void dismissAll() {
        BalloonEngine.dismissAll();
    }

    @NonNull
    private static BalloonModel toModel(@NonNull final Balloon balloon) {
        return new BalloonModel(
                balloon.id,
                balloon.title,
                balloon.message,
                balloon.icon,
                balloon.action,
                balloon.cancellable
        );
    }

}
