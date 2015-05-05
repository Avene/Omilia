package com.avene.avene.omilia;

import android.support.v7.widget.RecyclerView;
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
public class QuizListAdapter extends RecyclerView.Adapter<QuizListAdapter.ViewHolder> {
    private Quiz[] mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        @InjectView(R.id.sentence_en_textView)
        public TextView mSentenceEnTextView;

        @InjectView(R.id.sentence_jp_switch)
        public Switch mSentenceJpSwitch;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.inject(this, v);

            WidgetObservable.input(mSentenceJpSwitch).
                    subscribe(evt -> mSentenceEnTextView.setVisibility(evt.value() ? View.VISIBLE : View.INVISIBLE));
        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public QuizListAdapter(Quiz[] dataset) {
        mDataset = dataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public QuizListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sentence, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mSentenceJpSwitch.setText(mDataset[position].getQuestion());
        holder.mSentenceEnTextView.setText(mDataset[position].getAnswer());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}