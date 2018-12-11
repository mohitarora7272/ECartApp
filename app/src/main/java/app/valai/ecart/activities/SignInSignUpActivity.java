package app.valai.ecart.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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
public class SignInSignUpActivity extends BaseActivity {

    @BindView(R.id.rlSignInSignUp)
    RelativeLayout rlSignInSignUp;

    @BindView(R.id.llSignInSignUp)
    LinearLayout llSignInSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_signup);
        setUnBinder(ButterKnife.bind(this));
        setAnimationsOnView();
    }

    // Apply Animations On Views
    private void setAnimationsOnView() {
        AnimationUtil animationUtil = new AnimationUtil(this);
        animationUtil.slideInDown(rlSignInSignUp);
        animationUtil.slideInUp(llSignInSignUp);
    }

    // This Is For Attach To Base Context With Calligraphy Fonts
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @OnClick(R.id.btnSignIn)
    void openSignInActivity() {
        Intent intent = new Intent(this, SignInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.btnSignUp)
    void openSignUpActivity() {
        Intent intent = new Intent(this, SignUpActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}