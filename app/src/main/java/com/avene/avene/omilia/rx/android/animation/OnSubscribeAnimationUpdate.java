package com.avene.avene.omilia.rx.android.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.AndroidSubscriptions;
import rx.android.internal.Assertions;

/**
 * Created by yamai on 5/17/2015.
 */
public class OnSubscribeAnimationUpdate implements Observable.OnSubscribe<OnAnimationUpdateEvent> {
    private final boolean emitInitialValue;
    private final ValueAnimator mAnimator;

    public OnSubscribeAnimationUpdate(boolean emitInitialValue, ValueAnimator animator) {
        this.emitInitialValue = emitInitialValue;
        this.mAnimator = animator;
    }


    @Override
    public void call(Subscriber<? super OnAnimationUpdateEvent> subscriber) {
        Assertions.assertUiThread();
        ValueAnimator.AnimatorUpdateListener animationListener =
                animation -> subscriber.onNext(new OnAnimationUpdateEvent(mAnimator));

        final Subscription subscription =
                AndroidSubscriptions.unsubscribeInUiThread(() ->
                        mAnimator.removeUpdateListener(animationListener));

        if (emitInitialValue) {
            subscriber.onNext(new OnAnimationUpdateEvent(mAnimator));
        }

        mAnimator.addUpdateListener(animationListener);
        subscriber.add(subscription);
    }
}
