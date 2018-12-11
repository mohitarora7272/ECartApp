package app.valai.ecart.utils;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

import app.valai.ecart.R;

@SuppressWarnings("ALL")
public final class AnimationUtil implements Interpolator, Animation.AnimationListener {
    private Context ctx;
    private double mAmplitude = 1;
    private double mFrequency = 10;
    private View button = null;

    public AnimationUtil(Context ctx) {
        this.ctx = ctx;
    }

    private AnimationUtil(double amplitude, double frequency) {
        mAmplitude = amplitude;
        mFrequency = frequency;
    }

    // Bounce Animation
    public void bounceAnimation(View button) {
        Animation myAnim = AnimationUtils.loadAnimation(ctx, R.anim.bounce);
        button.startAnimation(myAnim);
    }

    // Bounce Interpolation Animation
    public void bounceInterpolationAnimation(View button) {
        Animation myAnim = AnimationUtils.loadAnimation(ctx, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        AnimationUtil interpolator = new AnimationUtil(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);
    }

    public void slideInLeft(final View button) {
        this.button = button;
        Animation myAnim = AnimationUtils.loadAnimation(ctx, R.anim.slide_in_left);
        myAnim.setAnimationListener(this);
        button.startAnimation(myAnim);
    }

    public void slideInRight(final View button) {
        this.button = button;
        Animation myAnim = AnimationUtils.loadAnimation(ctx, R.anim.slide_in_right);
        myAnim.setAnimationListener(this);
        button.startAnimation(myAnim);
    }

    public void slideInUp(final View button) {
        this.button = button;
        Animation myAnim = AnimationUtils.loadAnimation(ctx, R.anim.slide_in_up);
        myAnim.setAnimationListener(this);
        button.startAnimation(myAnim);
    }

    public void slideInDown(final View button) {
        this.button = button;
        Animation myAnim = AnimationUtils.loadAnimation(ctx, R.anim.slide_in_down);
        myAnim.setAnimationListener(this);
        button.startAnimation(myAnim);
    }

    public void slideOutDown(final View button) {
        this.button = button;
        Animation myAnim = AnimationUtils.loadAnimation(ctx, R.anim.slide_out_down);
        myAnim.setAnimationListener(this);
        button.startAnimation(myAnim);
    }

    public void slideOutLeft(final View button) {
        this.button = button;
        Animation myAnim = AnimationUtils.loadAnimation(ctx, R.anim.slide_out_left);
        myAnim.setAnimationListener(this);
        button.startAnimation(myAnim);
    }

    public void slideOutRight(final View button) {
        this.button = button;
        Animation myAnim = AnimationUtils.loadAnimation(ctx, R.anim.slide_out_right);
        myAnim.setAnimationListener(this);
        button.startAnimation(myAnim);
    }

    public void slideOutMicro(View button) {
        Animation myAnim = AnimationUtils.loadAnimation(ctx, R.anim.slide_out_micro);
        myAnim.setAnimationListener(this);
        button.startAnimation(myAnim);
    }

    @Override
    public float getInterpolation(float time) {
        return (float) (-1 * Math.pow(Math.E, -time / mAmplitude) *
                Math.cos(mFrequency * time) + 1);
    }

    @Override
    public void onAnimationStart(Animation animation) {
    }

    @Override
    public void onAnimationEnd(Animation animation) {
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
    }
}