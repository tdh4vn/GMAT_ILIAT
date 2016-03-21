package org.iliat.gmat.adapter;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.iliat.gmat.R;
import org.iliat.gmat.enitity.QuestionPack;
import org.iliat.gmat.enitity.QuestionPacks;

import java.util.List;

/**
 * Created by hungtran on 3/14/16.
 */
public class ListQuestionPackAdapter extends RecyclerView.Adapter<ListQuestionPackAdapter.QuestionPackViewHolder> {
    private List<QuestionPack> mListQuestionPack;
    public ListQuestionPackAdapter(){
        mListQuestionPack = QuestionPacks.getInst().getTodayQuestionPacks();
    }
    @Override
    public QuestionPackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_questionpack_layout, parent, false);
        QuestionPackViewHolder questionPackViewHolder = new QuestionPackViewHolder(v);
        return questionPackViewHolder;
    }

    @Override
    public void onBindViewHolder(QuestionPackViewHolder holder, int position) {
        holder.txtTime.setText(mListQuestionPack.get(position).getAvailable_time());
        holder.prbProcess.getProgressDrawable().setColorFilter(
                Color.parseColor("#FF5722"), android.graphics.PorterDuff.Mode.SRC_IN);
        holder.prbProcess.setMax(10);
        holder.prbProcess.setProgress(8);
    }

    @Override
    public int getItemCount() {
        return mListQuestionPack.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class QuestionPackViewHolder extends RecyclerView.ViewHolder{
        CardView cardMain;
        TextView txtTime;
        ProgressBar prbProcess;
        Button btnStart;
        public QuestionPackViewHolder(View itemView) {
            super(itemView);
            cardMain = (CardView) itemView.findViewById(R.id.card_question_pack);
            txtTime = (TextView) itemView.findViewById(R.id.question_pack_name);
            prbProcess = (ProgressBar) itemView.findViewById(R.id.progressBar);
            btnStart = (Button) itemView.findViewById(R.id.button_question_pack);
        }
    }
}
