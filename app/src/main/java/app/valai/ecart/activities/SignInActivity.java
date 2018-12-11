package app.valai.ecart.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import app.valai.ecart.R;
import app.valai.ecart.utils.AnimationUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * @author by Mohit Arora on 18/8/18.
 * @projectname ECartApp
 */
public class SignInActivity extends BaseActivity {

    @BindView(R.id.llMain)
    LinearLayout llMain;

    @BindView(R.id.tvHeader)
    TextView tvHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        setUnBinder(ButterKnife.bind(this));
        setAnimationsOnView();
    }

    // Apply Animations On Views
    private void setAnimationsOnView() {
        AnimationUtil animationUtil = new AnimationUtil(this);
        animationUtil.slideInDown(tvHeader);
        animationUtil.bounceAnimation(llMain);
    }

    // This Is For Attach To Base Context With Calligraphy Fonts
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @OnClick(R.id.tvCreateAccount)
    void onCreateAccountClick() {
        Intent intent = new Intent(this, SignUpActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.tvForgetPassword)
    void onForgetPasswordClick() {
        Intent intent = new Intent(this, ForgetPasswordActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @OnClick(R.id.btnSignIn)
    void onSignInClick() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}