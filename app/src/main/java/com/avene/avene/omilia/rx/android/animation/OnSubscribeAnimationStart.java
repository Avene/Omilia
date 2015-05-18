package com.avene.avene.omilia.rx.android.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.AndroidSubscriptions;
import rx.android.internal.Assertions;

/**
 * Created by yamai on 5/17/2015.
 */
public class OnSubscribeAnimationStart implements Observable.OnSubscribe<OnAnimationStartEvent>  {
    private final boolean emitInitialValue;
    private final Animator mAnimator;

    public OnSubscribeAnimationStart(boolean emitInitialValue, Animator animator) {
        this.emitInitialValue = emitInitialValue;
        this.mAnimator = animator;
    }


    @Override
    public void call(Subscriber<? super OnAnimationStartEvent> subscriber) {
        Assertions.assertUiThread();
        Animator.AnimatorListener animationListener = new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animator) {
                subscriber.onNext(new OnAnimationStartEvent(mAnimator));
            }
        };

        final Subscription subscription =
                AndroidSubscriptions.unsubscribeInUiThread(() ->
                        mAnimator.removeListener(animationListener));

        if(emitInitialValue){
            subscriber.onNext(new OnAnimationStartEvent(mAnimator));
        }

        mAnimator.addListener(animationListener);
        subscriber.add(subscription);
    }
}
