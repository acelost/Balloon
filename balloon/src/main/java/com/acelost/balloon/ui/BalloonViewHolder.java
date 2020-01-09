package com.acelost.balloon.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.acelost.balloon.R;
import com.acelost.balloon.action.BalloonAction;
import com.acelost.balloon.icon.BalloonIcon;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Preconditions;
import androidx.recyclerview.widget.RecyclerView;

final class BalloonViewHolder extends RecyclerView.ViewHolder {

    private final View rootView = itemView.findViewById(R.id.balloon_root);
    private final ImageView iconView = itemView.findViewById(R.id.balloon_icon);
    private final TextView titleView = itemView.findViewById(R.id.balloon_title);
    private final TextView messageView = itemView.findViewById(R.id.balloon_message);
    private final TextView actionView = itemView.findViewById(R.id.balloon_action);
    private final ImageView pinView = itemView.findViewById(R.id.balloon_pin);

    private boolean cancellable = false;

    BalloonViewHolder(@NonNull final View view,
            @NonNull final BalloonItemActionListener listener) {
        super(view);
        rootView.setOnClickListener(v -> {
            if (cancellable) {
                listener.onCancelBalloon(getAdapterPosition());
            }
        });
    }

    void bind(@NonNull final BalloonModel model) {
        Preconditions.checkNotNull(model);
        setupIcon(model.getIcon());
        setupTitle(model.getTitle());
        setupMessage(model.getMessage());
        setupMainAction(model.getAction());
        setupCancelAction(model.isCancellable());
    }

    private void setupIcon(@Nullable final BalloonIcon icon) {
        if (icon != null) {
            icon.draw(iconView);
            iconView.setVisibility(View.VISIBLE);
        } else {
            iconView.setImageResource(0);
            iconView.setVisibility(View.GONE);
        }
    }

    private void setupTitle(@Nullable final CharSequence title) {
        if (title != null) {
            titleView.setText(title);
            titleView.setVisibility(View.VISIBLE);
        } else {
            titleView.setText(null);
            titleView.setVisibility(View.GONE);
        }
    }

    private void setupMessage(@NonNull final CharSequence message) {
        Preconditions.checkNotNull(message);
        messageView.setText(message);
    }

    private void setupMainAction(@Nullable final BalloonAction action) {
        if (action != null) {
            actionView.setText(action.getLabel());
            actionView.setOnClickListener(v -> action.execute());
            actionView.setVisibility(View.VISIBLE);
        } else {
            actionView.setText(null);
            actionView.setOnClickListener(null);
            actionView.setVisibility(View.GONE);
        }
    }

    private void setupCancelAction(final boolean cancellable) {
        this.cancellable = cancellable;
        pinView.setVisibility(cancellable ? View.GONE : View.VISIBLE);
    }

}
