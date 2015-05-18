package com.avene.avene.omilia.rx.android.animation;

import android.animation.Animator;
import android.view.animation.Animation;

/**
 * Created by yamai on 5/17/2015.
 */
public class OnAnimationEndEvent {
    private Animator animator;

    public OnAnimationEndEvent(Animator animator) {
        this.animator = animator;
    }
}
