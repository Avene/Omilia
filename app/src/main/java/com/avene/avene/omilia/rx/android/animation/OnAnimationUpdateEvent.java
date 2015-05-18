package com.avene.avene.omilia.rx.android.animation;

import android.animation.ValueAnimator;

/**
 * Created by yamai on 5/17/2015.
 */
public class OnAnimationUpdateEvent {
    private ValueAnimator animator;

    public OnAnimationUpdateEvent(ValueAnimator animator) {
        this.animator = animator;
    }

    public Object getAnimatedValue(){
        return animator.getAnimatedValue();
    }

    public Object getAnimatedValue(String propName){
        return animator.getAnimatedValue(propName);
    }
}
