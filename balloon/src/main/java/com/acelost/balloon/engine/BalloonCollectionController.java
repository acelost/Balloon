package com.acelost.balloon.engine;

import com.acelost.balloon.base.MainThreadInteractor;
import com.acelost.balloon.ui.BalloonModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.AnyThread;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.core.util.Preconditions;

final class BalloonCollectionController extends MainThreadInteractor {

    @NonNull
    private final BalloonCollection balloons = new BalloonCollection();

    @NonNull
    private final Map<String, Runnable> deferredRemoves = new HashMap<>();

    @NonNull
    private final BalloonCollectionCallbacks listener;

    BalloonCollectionController(@NonNull final BalloonCollectionCallbacks listener) {
        this.listener = Preconditions.checkNotNull(listener);
    }

    @MainThread
    @NonNull
    List<BalloonModel> createSnapshot() {
        return balloons.createSnapshot();
    }

    @AnyThread
    void add(@NonNull final BalloonModel balloon, final long duration) {
        Preconditions.checkNotNull(balloon);
        doOnMainThread(() -> {
            if (balloons.add(balloon)) {
                if (duration > 0) {
                    final Runnable deferredRemove = () -> remove(balloon);
                    final String id = balloon.getOriginalId();
                    if (id != null) {
                        final Runnable pendingRemove = deferredRemoves.get(id);
                        if (pendingRemove != null) {
                            getMainHandler().removeCallbacks(pendingRemove);
                        }
                        deferredRemoves.put(id, deferredRemove);
                    }
                    getMainHandler().postDelayed(deferredRemove, duration);
                }
                listener.onCollectionChanged();
            }
        });
    }

    @AnyThread
    void remove(@NonNull final BalloonModel balloon) {
        Preconditions.checkNotNull(balloon);
        doOnMainThread(() -> {
            if (balloons.remove(balloon)) {
                final String originalId = balloon.getOriginalId();
                if (originalId != null) {
                    deferredRemoves.remove(originalId);
                }
                listener.onCollectionChanged();
            }
        });
    }

    @AnyThread
    void remove(@NonNull final String id) {
        Preconditions.checkNotNull(id);
        doOnMainThread(() -> {
            if (balloons.removeByOriginalId(id)) {
                deferredRemoves.remove(id);
                listener.onCollectionChanged();
            }
        });
    }

    @AnyThread
    void removeAll() {
        doOnMainThread(() -> {
            if (balloons.removeAll()) {
                deferredRemoves.clear();
                listener.onCollectionChanged();
            }
        });
    }
}
