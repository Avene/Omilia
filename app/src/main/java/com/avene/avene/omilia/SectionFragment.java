package com.avene.avene.omilia;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.avene.avene.omilia.model.Quiz;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A fragment representing a list of Items.
 * <p>
 * <p>
 * Activities containing this fragment MUST implement the {@link SectionFragment.SentenceFragmentListener}
 * interface.
 */
public class SectionFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SECTION_NAME = "sectionName";

    private String sectionName;

    private SentenceFragmentListener mListener;

    @InjectView(R.id.sentences_recycler_view)
    RecyclerView sentencesRecyclerView;

    public static SectionFragment newInstance(String sectionName) {
        SectionFragment fragment = new SectionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_NAME, sectionName);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SectionFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            sectionName = getArguments().getString(ARG_SECTION_NAME);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_section, container, false);

        ButterKnife.inject(this, view);


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        sentencesRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        sentencesRecyclerView.setLayoutManager(layoutManager);
        sentencesRecyclerView.setAdapter(new QuizListAdapter(getDataset()));

        return view;
    }

    private Quiz[] getDataset() {
        RealmResults<Quiz> quizzes =
                Realm.getInstance(App.getCtx()).where(Quiz.class).findAll();

        return quizzes.toArray(new Quiz[quizzes.size()]);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (SentenceFragmentListener) activity;
            ((MainActivity) activity).onSectionAttached(getArguments().getString(ARG_SECTION_NAME));
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement SentenceFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
//        super.onListItemClick(l, v, position, id);
//
//        if (null != mListener) {
//            // Notify the active callbacks interface (the activity, if the
//            // fragment is attached to one) that an item has been selected.
//            mListener.onInteraction(Sentences.ITEMS.get(position).id);
//        }
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface SentenceFragmentListener {
        // TODO: Update argument type and name
        public void onInteraction(String id);
    }

}
