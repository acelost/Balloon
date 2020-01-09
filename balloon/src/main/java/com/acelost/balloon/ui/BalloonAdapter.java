package com.acelost.balloon.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acelost.balloon.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ListAdapter;

public final class BalloonAdapter extends ListAdapter<BalloonModel, BalloonViewHolder> {

    @NonNull
    private final BalloonActionListener listActionListener;

    @NonNull
    private final BalloonItemActionListener itemActionListener = new BalloonItemActionListener() {
        @Override
        public void onCancelBalloon(final int adapterPosition) {
            final BalloonModel model = tryGetItem(adapterPosition);
            if (model != null) {
                listActionListener.onCancelBalloonClick(model);
            }
        }
    };

    public BalloonAdapter(@NonNull final BalloonActionListener listActionListener) {
        super(new BalloonDiffCallback());
        this.listActionListener = listActionListener;
    }

    @Nullable
    private BalloonModel tryGetItem(final int position) {
        if (position > -1 && position < getItemCount()) {
            return getItem(position);
        }
        return null;
    }

    @NonNull
    @Override
    public BalloonViewHolder onCreateViewHolder(@NonNull final ViewGroup parent,
            final int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.layout_balloon_item, parent, false);
        return new BalloonViewHolder(view, itemActionListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final BalloonViewHolder holder, final int position) {
        final BalloonModel model = getItem(position);
        holder.bind(model);
    }

}
