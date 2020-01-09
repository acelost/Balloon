package com.acelost.balloon.action;

import androidx.annotation.NonNull;
import androidx.core.util.Preconditions;

public abstract class BalloonAction {

    @NonNull
    private final CharSequence label;

    protected BalloonAction(@NonNull final CharSequence label) {
        this.label = Preconditions.checkNotNull(label);
    }

    public abstract void execute();

    @NonNull
    public CharSequence getLabel() {
        return label;
    }

    @NonNull
    public static BalloonAction fromRunnable(@NonNull final CharSequence label, @NonNull final Runnable runnable) {
        return new RunnableBalloonAction(label, runnable);
    }

}
