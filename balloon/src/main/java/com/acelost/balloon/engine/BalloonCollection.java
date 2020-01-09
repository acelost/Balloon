package com.acelost.balloon.engine;

import android.text.TextUtils;

import com.acelost.balloon.ui.BalloonModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import androidx.annotation.CheckResult;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.core.util.Preconditions;

@MainThread
final class BalloonCollection {

    @NonNull
    private final List<BalloonModel> items = new ArrayList<>();

    @NonNull
    List<BalloonModel> createSnapshot() {
        return new ArrayList<>(items);
    }

    @CheckResult
    boolean add(@NonNull final BalloonModel model) {
        Preconditions.checkNotNull(model);
        if (model.hasOriginalId()) {
            for (int i = 0; i < items.size(); i++) {
                final BalloonModel item = items.get(i);
                if (item.hasOriginalId() && TextUtils.equals(model.getOriginalId(), item.getOriginalId())) {
                    items.set(i, model);
                    return true;
                }
            }
        }
        items.add(model);
        return true;
    }

    @CheckResult
    boolean remove(@NonNull final BalloonModel model) {
        Preconditions.checkNotNull(model);
        if (model.hasOriginalId()) {
            final String originalId = model.getOriginalId();
            if (originalId != null && removeByOriginalId(originalId)) {
                return true;
            }
        }
        if (model.hasVirtualId()) {
            final int virtualId = model.getVirtualId();
            if (removeByVirtualId(virtualId)) {
                return true;
            }
        }
        return false;
    }

    @CheckResult
    boolean removeByOriginalId(@NonNull final String originalId) {
        Preconditions.checkNotNull(originalId);
        final Iterator<BalloonModel> iterator = items.iterator();
        while (iterator.hasNext()) {
            final BalloonModel item = iterator.next();
            if (item.hasOriginalId() && TextUtils.equals(originalId, item.getOriginalId())) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    @CheckResult
    boolean removeByVirtualId(final int virtualId) {
        final Iterator<BalloonModel> iterator = items.iterator();
        while (iterator.hasNext()) {
            final BalloonModel item = iterator.next();
            if (item.hasVirtualId() && virtualId == item.getVirtualId()) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    @CheckResult
    boolean removeAll() {
        if (!items.isEmpty()) {
            items.clear();
            return true;
        }
        return false;
    }

}
