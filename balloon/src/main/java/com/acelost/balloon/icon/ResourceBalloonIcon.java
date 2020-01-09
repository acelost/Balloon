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
}
