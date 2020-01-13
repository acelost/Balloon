package com.acelost.balloon.icon;

import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ResourceBalloonIcon extends TintedBalloonIcon {

    @DrawableRes
    private final int iconRes;

    public ResourceBalloonIcon(@DrawableRes final int iconRes, @Nullable final Integer tintColor) {
        super(tintColor);
        this.iconRes = iconRes;
    }

    @Override
    public void draw(@NonNull final ImageView imageView) {
        super.draw(imageView);
        imageView.setImageResource(iconRes);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        final ResourceBalloonIcon that = (ResourceBalloonIcon) o;

        return iconRes == that.iconRes;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + iconRes;
        return result;
    }
}
