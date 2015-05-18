package com.avene.avene.omilia.rx.android.animation;

import android.animation.Animator;

/**
 * Created by yamai on 5/17/2015.
 */
public class OnAnimationStartEvent {
    private Animator animator;

    public OnAnimationStartEvent(Animator animator) {
        this.animator = animator;
    }
}
