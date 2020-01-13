package com.acelost.balloon.ui;

import android.text.TextUtils;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public final class BalloonDiffCallback extends DiffUtil.ItemCallback<BalloonModel> {

    @Override
    public boolean areItemsTheSame(@NonNull final BalloonModel oldItem,
            @NonNull final BalloonModel newItem) {
        if (oldItem.hasOriginalId() && newItem.hasOriginalId()) {
            return TextUtils.equals(oldItem.getOriginalId(), newItem.getOriginalId());
        }
        if (oldItem.hasVirtualId() && newItem.hasVirtualId()) {
            return oldItem.getVirtualId() == newItem.getVirtualId();
        }
        return false;
    }

    @Override
    public boolean areContentsTheSame(@NonNull final BalloonModel oldItem,
            @NonNull final BalloonModel newItem) {
        if (!TextUtils.equals(oldItem.getTitle(), newItem.getTitle())) {
            return false;
        }
        if (!TextUtils.equals(oldItem.getMessage(), newItem.getMessage())) {
            return false;
        }
        if (oldItem.isCancellable() != newItem.isCancellable()) {
            return false;
        }
        if (!Objects.equals(oldItem.getIcon(), newItem.getIcon())) {
            return false;
        }
        if (!Objects.equals(oldItem.getAction(), newItem.getAction())) {
            return false;
        }
        return true;
    }

}
