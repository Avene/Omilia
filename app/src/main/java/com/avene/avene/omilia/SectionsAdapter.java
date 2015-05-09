package com.avene.avene.omilia;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.avene.avene.omilia.model.Section;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.android.widget.WidgetObservable;

/**
 * Created by yamai on 5/8/2015.
 */
public class SectionsAdapter extends RecyclerView.Adapter<SectionsAdapter.ViewHolder> {
    private Section[] mDataset;

    public SectionsAdapter(Section[] mDataset) {
        this.mDataset = mDataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_section, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.section_textView.setText(mDataset[position].getName());

    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @InjectView(R.id.section_textView)
        public TextView section_textView;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.inject(this, v);

        }
    }
}
