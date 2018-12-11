package app.valai.ecart.activities;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import app.valai.ecart.R;
import app.valai.ecart.adapters.IntroViewPagerAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * @author by Mohit Arora on 30/8/18.
 * @projectname ECartApp
 */
public class ProductDetailsActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.btnBack)
    ImageButton btnBack;

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.viewPager)
    RollPagerView viewPager;

    @BindView(R.id.btnPlus)
    Button btnPlus;

    @BindView(R.id.btnMinus)
    Button btnMinus;

    @BindView(R.id.edtRange)
    EditText edtRange;

    private Integer[] mImageResources = {
            R.drawable.logo,
            R.drawable.logo
    };

    // This Is For Attach To Base Context With Calligraphy Fonts
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        setUnBinder(ButterKnife.bind(this));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        btnBack.setVisibility(View.VISIBLE);
        tvTitle.setVisibility(View.VISIBLE);
        setViewPager();
        edtRange.setText("1");
        edtRange.setCursorVisible(false);
        edtRange.setSelection(edtRange.getText().length());
    }

    // Initialize View Pager
    private void setViewPager() {
        viewPager.setPlayDelay(5000);
        viewPager.setAnimationDurtion(2000);
        viewPager.setHintPadding(0, 0, 0, 100);
        viewPager.setHintView(new ColorPointHintView(this,
                Color.parseColor("#689f38"), Color.parseColor("#C8E6C9")));
        IntroViewPagerAdapter mAdapter = new IntroViewPagerAdapter(viewPager, mImageResources);
        viewPager.setAdapter(mAdapter);
    }

    @OnClick(R.id.btnBack)
    void onBackClick() {
        finish();
    }

    @OnClick(R.id.btnPlus)
    void onPlusClick() {
        hideKeyboard();
        if (edtRange.getText() != null && !edtRange.getText().toString().equals("")) {
            edtRange.setText(String.valueOf(Integer.parseInt(edtRange.getText().toString()) + 1));
        } else {
            edtRange.setText("1");
        }
    }

    @OnClick(R.id.btnMinus)
    void onMinusClick() {
        hideKeyboard();
        if (edtRange.getText() != null && edtRange.getText().toString().equals("1")) {
            return;
        }
        if (edtRange.getText() != null && !edtRange.getText().toString().equals("")) {
            edtRange.setText(String.valueOf(Integer.parseInt(edtRange.getText().toString()) - 1));
        } else {
            edtRange.setText("1");
        }
    }
}