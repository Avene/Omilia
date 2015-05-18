package com.avene.avene.omilia;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.avene.avene.omilia.model.Quiz;
import com.avene.avene.omilia.model.Section;
import com.avene.avene.omilia.rx.android.animation.AnimationObservable;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.realm.Realm;
import io.realm.RealmList;
import rx.android.widget.WidgetObservable;

/**
 * A fragment representing a list of Items.
 * <p>
 * <p>
 * Activities containing this fragment MUST implement the {@link QuizzesFragmentListener}
 * interface.
 */
public class QuizzesFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SECTION_NO = "sectionNo";


    private Section section;

    private QuizzesFragmentListener mListener;

    @InjectView(R.id.quizzes_recycler_view)
    RecyclerView quizzesRecyclerView;

    @InjectView(R.id.overview_wrapper)
    FrameLayout overview_wrapper;

    int mOverviewHeight = 0;

    @InjectView(R.id.overview_dimmer)
    View overview_dimmer;

    @InjectView(R.id.overview_top_shadow_view)
    View overview_top_shadow_view;

    @InjectView(R.id.overview_bottom_shadow_view)
    View overview_bottom_shadow_view;

    @InjectView(R.id.overview_body)
    LinearLayout overview_body;

    @InjectView(R.id.overview_textView)
    TextView overview_textView;

    @InjectView(R.id.tips_textView)
    TextView tips_textView;

    @InjectView(R.id.title_textView)
    TextView title_textView;

    @InjectView(R.id.overview_toggle_toggleButton)
    ToggleButton overviewToggleToggleButton;

    private final float OVERVIEW_SCALE_FACTOR = 0.03f;

    public static QuizzesFragment newInstance(Integer sectionNo) {
        QuizzesFragment fragment = new QuizzesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NO, sectionNo);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public QuizzesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout rootView = (LinearLayout) inflater.inflate(R.layout.fragment_quizzes, container, false);
        ButterKnife.inject(this, rootView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        quizzesRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        quizzesRecyclerView.setLayoutManager(layoutManager);
        quizzesRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        quizzesRecyclerView.setAdapter(new QuizzesAdapter(getDataset()));

        title_textView.setText(section.getName());
        overview_textView.setText(section.getOverview());
        tips_textView.setText(section.getTips());

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        measureOverviewHeight();
        setToggleAnimations();
    }

    private void measureOverviewHeight() {
        overview_wrapper.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mOverviewHeight = overview_wrapper.getHeight();
                overview_wrapper.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                overview_wrapper.setVisibility(View.GONE);
            }
        });
    }

    private void setToggleAnimations() {

        ValueAnimator slideDownAnimator = ValueAnimator.ofFloat(0f, 1f).setDuration(150);

        slideDownAnimator.addUpdateListener(animation -> {
            overview_wrapper.getLayoutParams().height =
                    (int) ((float) animation.getAnimatedValue() * mOverviewHeight);
            overview_dimmer.getLayoutParams().height =
                    (int) ((float) animation.getAnimatedValue() * mOverviewHeight);
            overview_wrapper.setAlpha((Float) animation.getAnimatedValue());
            overview_wrapper.requestLayout();
            overview_dimmer.requestLayout();
        });

        slideDownAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                overview_wrapper.setVisibility(View.VISIBLE);
                overview_top_shadow_view.setAlpha(1f);
                overview_bottom_shadow_view.setAlpha(1f);
                overview_dimmer.setAlpha(0.5f);
                overview_body.setScaleX(1 - OVERVIEW_SCALE_FACTOR);
                overview_body.setScaleY(1 - OVERVIEW_SCALE_FACTOR);
            }
        });

        AnimationObservable.end(slideDownAnimator).subscribe(onAnimationEndEvent -> {
            ValueAnimator dimmerAnimator = ValueAnimator.ofFloat(1f, 0f);
            dimmerAnimator.addUpdateListener(animation1 -> {
                float val = (float) animation1.getAnimatedValue();
                overview_top_shadow_view.setAlpha(val);
                overview_bottom_shadow_view.setAlpha(val);
                overview_dimmer.setAlpha(val / 2);
                overview_body.setScaleX(1 - (val * OVERVIEW_SCALE_FACTOR));
                overview_body.setScaleY(1 - (val * OVERVIEW_SCALE_FACTOR));
            });
            dimmerAnimator.setStartDelay(100);
            dimmerAnimator.setDuration(100);
            dimmerAnimator.start();


        });

        ValueAnimator slideUpAnimator = ValueAnimator.ofFloat(1f, 0f).setDuration(100);
        slideUpAnimator.addUpdateListener(animation -> {
            overview_wrapper.getLayoutParams().height =
                    (int) ((float) animation.getAnimatedValue() * mOverviewHeight);
            overview_wrapper.requestLayout();
        });
        slideUpAnimator.setStartDelay(100);
        AnimationObservable.end(slideUpAnimator).subscribe(evt -> {
            overview_wrapper.setVisibility(View.GONE);
        });

        ValueAnimator shrinkAnimator = ValueAnimator.ofFloat(0f, 1f).setDuration(150);
        shrinkAnimator.addUpdateListener(animation -> {
            float val = (float) animation.getAnimatedValue();
            float shadowAlpha = val * 2;
            float dimmerAlpha = shadowAlpha - 1f;
            overview_top_shadow_view.setAlpha(shadowAlpha);
            overview_bottom_shadow_view.setAlpha(shadowAlpha);
            overview_dimmer.setAlpha(dimmerAlpha);

            overview_body.setScaleX(1 - (val * OVERVIEW_SCALE_FACTOR));
            overview_body.setScaleY(1 - (val * OVERVIEW_SCALE_FACTOR));
        });
        AnimationObservable.end(shrinkAnimator).subscribe(evt -> {
            slideUpAnimator.start();
        });

        WidgetObservable.input(overviewToggleToggleButton).subscribe(evt -> {
            if (evt.value()) {
                slideDownAnimator.start();
            } else {
                shrinkAnimator.start();
            }
        });
    }

    private Quiz[] getDataset() {
        RealmList<Quiz> quizzes = section.getQuizzes();
        return quizzes.toArray(new Quiz[quizzes.size()]);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (QuizzesFragmentListener) activity;
            if (getArguments() != null) {
                section = Realm.getInstance(App.getCtx())
                        .where(Section.class).equalTo("sectionNo", getArguments().getInt(ARG_SECTION_NO))
                        .findFirst();
                ((MainActivity) activity).onSectionAttached(section.getName());
            }
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement QuizzesFragmentListener");
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
//            mListener.onQuizzesInteraction(Sentences.ITEMS.get(position).id);
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
    public interface QuizzesFragmentListener {
        // TODO: Update argument type and name
        public void onQuizzesInteraction(String id);
    }

}
