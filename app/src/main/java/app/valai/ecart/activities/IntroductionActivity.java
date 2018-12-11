package app.valai.ecart.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import app.valai.ecart.R;
import app.valai.ecart.adapters.IntroViewPagerAdapter;
import app.valai.ecart.utils.AnimationUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * @author by Mohit Arora on 18/8/18.
 * @projectname ECartApp
 */
public class IntroductionActivity extends BaseActivity {

    @BindView(R.id.btnLetsShopping)
    Button btnLetsShopping;

    @BindView(R.id.viewPager)
    RollPagerView viewPager;

    @BindView(R.id.rlViewPager)
    RelativeLayout rlViewPager;

    // Initialize Image Resources
    private Integer[] mImageResources = {
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
        setUnBinder(ButterKnife.bind(this));
        setAnimationsOnView();

        viewPager.setPlayDelay(5000);
        viewPager.setAnimationDurtion(2000);
        viewPager.setHintPadding(0, 0, 0, 100);
        viewPager.setHintView(new ColorPointHintView(this,
                Color.parseColor("#689f38"), Color.parseColor("#C8E6C9")));
        IntroViewPagerAdapter mAdapter = new IntroViewPagerAdapter(viewPager, mImageResources);
        viewPager.setAdapter(mAdapter);
    }

    // Apply Animations On Views
    private void setAnimationsOnView() {
        AnimationUtil animationUtil = new AnimationUtil(this);
        animationUtil.slideInDown(rlViewPager);
        animationUtil.slideInUp(btnLetsShopping);
    }

    // This Is For Attach To Base Context With Calligraphy Fonts
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @OnClick(R.id.btnLetsShopping)
    void onStartShoppingClick() {
        if (viewPager.isPlaying()) {
            viewPager.pause();
        }
        Intent intent = new Intent(this, SignInSignUpActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}