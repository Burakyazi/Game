package com.bilgeadam.xox.presentation.contacts.scoreBoard;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bilgeadam.xox.R;
import org.jetbrains.annotations.NotNull;

public class ScoreBoardViewHolder extends RecyclerView.ViewHolder {

    private final TextView order, playerName, playerScore;

    protected TextView getOrder() {
        return order;
    }

    protected TextView getPlayerName() {
        return playerName;
    }

    protected TextView getPlayerScore() {
        return playerScore;
    }

    public ScoreBoardViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        order = itemView.findViewById(R.id.score_order);
        playerName = itemView.findViewById(R.id.player_name);
        playerScore = itemView.findViewById(R.id.player_score);
    }
}
