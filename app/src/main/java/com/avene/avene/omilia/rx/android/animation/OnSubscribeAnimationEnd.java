package com.avene.avene.omilia.rx.android.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.AndroidSubscriptions;
import rx.android.internal.Assertions;
import rx.functions.Action0;

/**
 * Created by yamai on 5/17/2015.
 */
public class OnSubscribeAnimationEnd implements Observable.OnSubscribe<OnAnimationEndEvent>  {
    private final boolean emitInitialValue;
    private final Animator mAnimator;

    public OnSubscribeAnimationEnd(boolean emitInitialValue, Animator animator) {
        this.emitInitialValue = emitInitialValue;
        this.mAnimator = animator;
    }


    @Override
    public void call(Subscriber<? super OnAnimationEndEvent> subscriber) {
        Assertions.assertUiThread();
        Animator.AnimatorListener animationListener = new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animator) {
                subscriber.onNext(new OnAnimationEndEvent(mAnimator));
            }
        };

        final Subscription subscription =
                AndroidSubscriptions.unsubscribeInUiThread(() ->
                        mAnimator.removeListener(animationListener));

        if(emitInitialValue){
            subscriber.onNext(new OnAnimationEndEvent(mAnimator));
        }

        mAnimator.addListener(animationListener);
        subscriber.add(subscription);
    }
}
