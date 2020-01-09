package com.acelost.balloon.ui;

import com.acelost.balloon.action.BalloonAction;
import com.acelost.balloon.icon.BalloonIcon;

import java.util.concurrent.atomic.AtomicInteger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Preconditions;

public final class BalloonModel {

    private static final int UNDEFINED_VIRTUAL_ID = -1;

    private static final AtomicInteger nextVirtualId = new AtomicInteger();

    @Nullable
    private final String originalId;

    private final int virtualId;

    @Nullable
    private final CharSequence title;

    @NonNull
    private final CharSequence message;

    @Nullable
    private final BalloonIcon icon;

    @Nullable
    private final BalloonAction action;

    private final boolean cancellable;

    public BalloonModel(@Nullable final String id,
            @Nullable final CharSequence title,
            @NonNull final CharSequence message,
            @Nullable final BalloonIcon icon,
            @Nullable final BalloonAction action,
            final boolean cancellable) {
        this.originalId = id;
        this.virtualId = generateVirtualId(id);
        this.title = title;
        this.message = Preconditions.checkNotNull(message);
        this.icon = icon;
        this.action = action;
        this.cancellable = cancellable;
    }

    public boolean hasOriginalId() {
        return originalId != null;
    }

    public boolean hasVirtualId() {
        return virtualId != UNDEFINED_VIRTUAL_ID;
    }

    @Nullable
    public String getOriginalId() {
        return originalId;
    }

    public int getVirtualId() {
        return virtualId;
    }

    @Nullable
    public CharSequence getTitle() {
        return title;
    }

    @NonNull
    public CharSequence getMessage() {
        return message;
    }

    @Nullable
    public BalloonIcon getIcon() {
        return icon;
    }

    @Nullable
    public BalloonAction getAction() {
        return action;
    }

    public boolean isCancellable() {
        return cancellable;
    }

    private static int generateVirtualId(@Nullable final String id) {
        return id == null ? nextVirtualId.getAndIncrement() : UNDEFINED_VIRTUAL_ID;
    }

}
