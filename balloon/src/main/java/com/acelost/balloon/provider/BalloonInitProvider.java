package com.acelost.balloon.provider;

import android.app.Application;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.acelost.balloon.engine.BalloonEngine;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BalloonInitProvider extends ContentProvider {

    @Override
    public boolean onCreate() {
        final Context context = getContext();
        if (context instanceof Application) {
            BalloonEngine.launch((Application) context);
        } else {
            Log.e("BalloonInitProvider", "Failed to initialize Balloon");
        }
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull final Uri uri, @Nullable final String[] projection,
            @Nullable final String selection,
            @Nullable final String[] selectionArgs, @Nullable final String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull final Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull final Uri uri, @Nullable final ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull final Uri uri, @Nullable final String selection,
            @Nullable final String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull final Uri uri, @Nullable final ContentValues values,
            @Nullable final String selection,
            @Nullable final String[] selectionArgs) {
        return 0;
    }
}
