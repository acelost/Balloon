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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final RunnableBalloonAction that = (RunnableBalloonAction) o;

        return runnable.equals(that.runnable);
    }

    @Override
    public int hashCode() {
        return runnable.hashCode();
    }
}
