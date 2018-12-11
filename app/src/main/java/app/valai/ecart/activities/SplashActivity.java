package app.valai.ecart.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import app.valai.ecart.R;
import app.valai.ecart.utils.AnimationUtil;
import app.valai.ecart.utils.AppConstants;
import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * @author by Mohit Arora on 22/8/18.
 * @projectname ECartApp
 */
public class SplashActivity extends BaseActivity {

    @BindView(R.id.imgView)
    ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getAppPreferenceHelper().isFirstInstall()) {
//            openNextActivity();
//            return;
//        }
        setContentView(R.layout.activity_splash);
        // Bind Views
        setUnBinder(ButterKnife.bind(this));
        // Apply Animations
        setAnimationsOnView();

        /*
         * Showing splash screen with a timer. This will be useful when you
         * want to show case your app logo / company
         */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //getAppPreferenceHelper().setFirstInstall(true);
                openNextActivity();
            }
        }, AppConstants.SPLASH_TIME_OUT);
    }

    // Apply Animations On Views
    private void setAnimationsOnView() {
        AnimationUtil animationUtil = new AnimationUtil(this);
        animationUtil.bounceAnimation(imgView);
    }

    // This Is For Attach To Base Context With Calligraphy Fonts
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    // Open Activity Intent
    private void openNextActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}