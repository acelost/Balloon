package com.acelost.balloon.action;

import androidx.annotation.NonNull;
import androidx.core.util.Preconditions;

public final class RunnableBalloonAction extends BalloonAction {

    @NonNull
    private final Runnable runnable;

    public RunnableBalloonAction(@NonNull final CharSequence label, @NonNull final Runnable runnable) {
        super(label);
        this.runnable = Preconditions.checkNotNull(runnable);
    }

    @Override
    public void execute() {
        runnable.run();
    }
}
