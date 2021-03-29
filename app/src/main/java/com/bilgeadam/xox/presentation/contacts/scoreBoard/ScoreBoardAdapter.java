package com.bilgeadam.xox.presentation.contacts.scoreBoard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bilgeadam.xox.R;
import com.bilgeadam.xox.data.Score;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ScoreBoardAdapter extends RecyclerView.Adapter<ScoreBoardViewHolder> {

    private List<Score> scoreList;

    @NonNull
    @NotNull
    @Override
    public ScoreBoardViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scores_row,parent,false);
        return new ScoreBoardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ScoreBoardViewHolder holder, int position) {
        holder.getOrder().setText( scoreList.get(position).getId());
        holder.getPlayerName().setText(Float.toString(scoreList.get(position).getScore()));
        holder.getPlayerScore().setText(scoreList.get(position).getUsername());

    }
    @Override
    public int getItemCount() {
        return scoreList != null ? scoreList.size() : 0;
    }

    public void setScoreList(List<Score> scoreList) {
        this.scoreList = scoreList;
    }


}
