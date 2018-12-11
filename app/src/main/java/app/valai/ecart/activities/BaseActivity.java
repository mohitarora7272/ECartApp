/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package app.valai.ecart.activities;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import app.valai.ecart.R;
import app.valai.ecart.fragments.BaseFragment;
import app.valai.ecart.interfaces.MvpView;
import app.valai.ecart.prefs.AppPreferencesHelper;
import app.valai.ecart.utils.AppConstants;
import app.valai.ecart.utils.CommonUtils;
import app.valai.ecart.utils.KeyboardUtils;
import app.valai.ecart.utils.MarshmallowPermissions;
import app.valai.ecart.utils.NetworkUtils;
import butterknife.Unbinder;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * @author by Mohit Arora on 22/8/18.
 * @projectname ECartApp
 */
@SuppressWarnings("deprecation")
public abstract class BaseActivity extends AppCompatActivity implements MvpView, BaseFragment.Callback {

    private ProgressDialog mProgressDialog;
    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // This Is For Attach To Base Context With Calligraphy Fonts
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    // Request API Level >= 23 Runtime Permissions
    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    // Check API Level >= 23 Runtime Permissions
    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    // Show Progress Loading
    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    // Hide Progress Loading
    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    // Show SnackBar Message
    private void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
        snackbar.show();
    }

    // Showing Error Message With String Parameter Pass
    @Override
    public void onError(String message) {
        if (message != null) {
            showSnackBar(message);
        } else {
            showSnackBar(getString(R.string.some_error));
        }
    }

    // Showing Error Message With Integer ResourceId Pass
    @Override
    public void onError(@StringRes int resId) {
        onError(getString(resId));
    }

    // Showing Message Using With String Parameter Pass
    @Override
    public void showMessage(String message) {
        if (message != null) {
            showSnackBar(message);
        } else {
            showSnackBar(getString(R.string.some_error));
        }
    }

    // Showing Message Using With Integer ResourceId Pass
    @Override
    public void showMessage(@StringRes int resId) {
        showMessage(getString(resId));
    }

    // Check is Network Connected Or Not
    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    // Check API Level >= 23 Runtime Permissions Granted
    @Override
    public boolean isPermissionsGranted(String permission) {
        return MarshmallowPermissions.checkPermission(this, permission);
    }

    // Request API Level >= 23 Runtime Permissions
    @Override
    public void requestPermission(String[] permission, int requestPermissionCode) {
        MarshmallowPermissions.requestPermissions(this, permission, requestPermissionCode);
    }

    // Fragment Attach
    @Override
    public void onFragmentAttached() {
    }

    // Fragment Detach Using Tag
    @Override
    public void onFragmentDetached(String tag) {

    }

    // Hide Keyboard
    @Override
    public void hideKeyboard() {
        KeyboardUtils.hideSoftInput(this);
    }

    // UnBind Views
    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    // Destroy/Unbind Views
    @Override
    protected void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }

    // App Preference Helper Name
    @Override
    public AppPreferencesHelper getAppPreferenceHelper() {
        return new AppPreferencesHelper(this, AppConstants.PREF_NAME);
    }
}