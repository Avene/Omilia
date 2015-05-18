package com.avene.avene.omilia.rx.android.animation;

import android.animation.Animator;

import rx.Observable;

/**
 * Created by yamai on 5/17/2015.
 */
public final class AnimationObservable {

    private AnimationObservable() {
        throw new AssertionError("No instances");
    }

    public static Observable<OnAnimationEndEvent> end(Animator animator) {
        return Observable.create(new OnSubscribeAnimationEnd(false, animator));
    }

    public static Observable<OnAnimationEndEvent> end(boolean emitInitialValue, Animator animator) {
        return Observable.create(new OnSubscribeAnimationEnd(emitInitialValue, animator));
    }

    public static Observable<OnAnimationStartEvent> start(Animator animator) {
        return Observable.create(new OnSubscribeAnimationStart(false, animator));
    }

    public static Observable<OnAnimationStartEvent> start(boolean emitInitialValue, Animator animator) {
        return Observable.create(new OnSubscribeAnimationStart(emitInitialValue, animator));
    }
}
