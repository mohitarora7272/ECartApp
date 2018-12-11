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

package app.valai.ecart.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.View;

import app.valai.ecart.activities.BaseActivity;
import app.valai.ecart.interfaces.MvpView;
import app.valai.ecart.prefs.AppPreferencesHelper;
import app.valai.ecart.utils.CommonUtils;
import app.valai.ecart.utils.MarshmallowPermissions;
import butterknife.Unbinder;

/**
 * @author by Mohit Arora on 22/8/18.
 * @projectname ECartApp
 */
@SuppressWarnings("deprecation")
public abstract class BaseFragment extends Fragment implements MvpView {

    private BaseActivity mActivity;
    private Unbinder mUnBinder;
    private ProgressDialog mProgressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    // Attach BaseFragment With BaseActivity Using Instance
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            this.mActivity = (BaseActivity) context;
        }
    }

    // Show Progress Loading
    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this.getContext());
    }

    // Hide Progress Loading
    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    // Showing Error Message With String Parameter Pass
    @Override
    public void onError(String message) {
        if (mActivity != null) {
            mActivity.onError(message);
        }
    }

    // Showing Error Message Using With Integer ResourceId Pass
    @Override
    public void onError(@StringRes int resId) {
        if (mActivity != null) {
            mActivity.onError(resId);
        }
    }

    // Showing Error Message Using With String Parameter Pass
    @Override
    public void showMessage(String message) {
        if (mActivity != null) {
            mActivity.showMessage(message);
        }
    }

    // Showing Message Using With Integer ResourceId Pass
    @Override
    public void showMessage(@StringRes int resId) {
        if (mActivity != null) {
            mActivity.showMessage(resId);
        }
    }

    // Check is Network Connected Or Not
    @Override
    public boolean isNetworkConnected() {
        return mActivity != null && mActivity.isNetworkConnected();
    }

    // Check API Level >= 23 Runtime Permissions Granted
    @Override
    public boolean isPermissionsGranted(String permission) {
        return MarshmallowPermissions.checkPermission(mActivity, permission);
    }

    // Request API Level >= 23 Runtime Permissions
    @Override
    public void requestPermission(String[] permission, int requestPermissionCode) {
        MarshmallowPermissions.requestPermissions(mActivity, permission, requestPermissionCode);
    }

    // Detach Activity With Fragment
    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    // Hide Keyboard
    @Override
    public void hideKeyboard() {
        if (mActivity != null) {
            mActivity.hideKeyboard();
        }
    }

    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    // UnBind Views
    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    // App Preference Helper Name
    @Override
    public AppPreferencesHelper getAppPreferenceHelper() {
        return mActivity.getAppPreferenceHelper();
    }

    // Destroy/Unbind Views
    @Override
    public void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }

    // Call Back Fragments
    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }
}