package com.acelost.balloon.icon;

import android.content.res.ColorStateList;
import android.widget.ImageView;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class TintedBalloonIcon extends BalloonIcon {

    @Nullable
    private final Integer tintColor;

    protected TintedBalloonIcon(@Nullable final Integer tintColor) {
        this.tintColor = tintColor;
    }

    @CallSuper
    @Override
    public void draw(@NonNull final ImageView imageView) {
        if (tintColor != null) {
            final ColorStateList tintList = ColorStateList.valueOf(tintColor);
            imageView.setImageTintList(tintList);
        } else {
            imageView.setImageTintList(null);
        }
    }
}
