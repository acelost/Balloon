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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final TintedBalloonIcon that = (TintedBalloonIcon) o;

        return tintColor != null ? tintColor.equals(that.tintColor) : that.tintColor == null;
    }

    @Override
    public int hashCode() {
        return tintColor != null ? tintColor.hashCode() : 0;
    }
}
