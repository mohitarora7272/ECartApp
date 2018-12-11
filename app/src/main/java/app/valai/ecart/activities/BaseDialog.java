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

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;

import app.valai.ecart.R;
import app.valai.ecart.interfaces.DialogMvpView;
import app.valai.ecart.prefs.AppPreferencesHelper;
import app.valai.ecart.utils.MarshmallowPermissions;
import butterknife.Unbinder;

/**
 * @author by Mohit Arora on 22/8/18.
 * @projectname ECartApp
 */
public abstract class BaseDialog extends DialogFragment implements DialogMvpView {

    private BaseActivity mActivity;
    private Unbinder mUnBinder;

    // Attach BaseDialog Fragment With BaseActivity Using Instance
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity mActivity = (BaseActivity) context;
            this.mActivity = mActivity;
            mActivity.onFragmentAttached();
        }
    }

    // Show Progress Loading
    @Override
    public void showLoading() {
        if (mActivity != null) {
            mActivity.showLoading();
        }
    }

    // Hide Progress Loading
    @Override
    public void hideLoading() {
        if (mActivity != null) {
            mActivity.hideLoading();
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

    protected abstract void setUp(View view);

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // The content
        final RelativeLayout root = new RelativeLayout(getActivity());
        root.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        // Creating the fullscreen dialog
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(root);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            dialog.getWindow().setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
        }
        dialog.setCanceledOnTouchOutside(false);

        return dialog;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUp(view);
    }

    // Showing Fragment
    public void show(FragmentManager fragmentManager, String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment prevFragment = fragmentManager.findFragmentByTag(tag);
        if (prevFragment != null) {
            transaction.remove(prevFragment);
        }
        transaction.addToBackStack(null);
        show(transaction, tag);
    }

    // Dismiss Fragment Dialog
    @Override
    public void dismissDialog(String tag) {
        dismiss();
        getBaseActivity().onFragmentDetached(tag);
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
}