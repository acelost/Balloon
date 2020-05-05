package com.acelost.balloon.icon;

import android.graphics.Color;
import android.widget.ImageView;

import com.acelost.balloon.R;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class BalloonIcon {

    public abstract void draw(@NonNull ImageView imageView);

    @NonNull
    public static BalloonIcon fromResource(@DrawableRes final int iconRes) {
        return fromResource(iconRes, null);
    }

    @NonNull
    public static BalloonIcon fromResource(@DrawableRes final int iconRes, @Nullable final Integer tintColor) {
        return new ResourceBalloonIcon(iconRes, tintColor);
    }

    @NonNull
    public static BalloonIcon info() {
        return info(null);
    }

    @NonNull
    public static BalloonIcon info(@Nullable final Integer tintColor) {
        final int color = tintColor != null ? tintColor : Color.rgb(78, 145, 191);
        return fromResource(R.drawable.ic_info_24_white, color);
    }

    @NonNull
    public static BalloonIcon warning() {
        return warning(null);
    }

    @NonNull
    public static BalloonIcon warning(@Nullable final Integer tintColor) {
        final int color = tintColor != null ? tintColor : Color.rgb(201, 67, 61);
        return fromResource(R.drawable.ic_warning_24_white, color);
    }

    @NonNull
    public static BalloonIcon notification() {
        return notification(null);
    }

    @NonNull
    public static BalloonIcon notification(@Nullable final Integer tintColor) {
        final int color = tintColor != null ? tintColor : Color.rgb(247, 220, 77);
        return fromResource(R.drawable.ic_notification_24_white, color);
    }

    @NonNull
    public static BalloonIcon check() {
        return check(null);
    }

    @NonNull
    public static BalloonIcon check(@Nullable final Integer tintColor) {
        final int color = tintColor != null ? tintColor : Color.rgb(79, 192, 37);
        return fromResource(R.drawable.ic_check_24_white, color);
    }

    @NonNull
    public static BalloonIcon cancel() {
        return cancel(null);
    }

    @NonNull
    public static BalloonIcon cancel(@Nullable final Integer tintColor) {
        final int color = tintColor != null ? tintColor : Color.rgb(225, 2, 2);
        return fromResource(R.drawable.ic_cancel_24_white, color);
    }

}
