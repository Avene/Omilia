package com.avene.avene.omilia;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.avene.avene.omilia.model.Quiz;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.android.widget.WidgetObservable;

/**
 * Created by yamai on 4/25/2015.
 */
public class QuizzesAdapter extends RecyclerView.Adapter<QuizzesAdapter.ViewHolder> {
    private Quiz[] mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        @InjectView(R.id.answer_textView)
        public TextView answerTextView;

        @InjectView(R.id.question_switch)
        public Switch questionSwitch;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.inject(this, v);

            WidgetObservable.input(questionSwitch)
                    .subscribe(evt -> {
                        float[] alphaValues = evt.value() ? new float[]{0f, 1f} : new float[]{1f, 0f};
                        ObjectAnimator.ofFloat(answerTextView, "alpha", alphaValues)
                                .setDuration(150)
                                .start();
                    });
        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public QuizzesAdapter(Quiz[] dataset) {
        mDataset = dataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public QuizzesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_quiz, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.questionSwitch.setText(mDataset[position].getQuestion());
        holder.answerTextView.setText(mDataset[position].getAnswer());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}