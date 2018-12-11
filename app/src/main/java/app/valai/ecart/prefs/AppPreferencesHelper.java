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

package app.valai.ecart.prefs;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author by Mohit Arora on 26/8/18.
 * @projectname ECartApp
 */
public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_KEY_IS_FIRST_INSTALL = "PREF_KEY_IS_FIRST_INSTALL";

    private static final String PREF_KEY_IS_USER_LOGIN = "PREF_KEY_IS_USER_LOGIN";

    private final SharedPreferences mPrefs;

    public AppPreferencesHelper(Context context, String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public Boolean isFirstInstall() {
        return mPrefs.getBoolean(PREF_KEY_IS_FIRST_INSTALL, false);
    }

    @Override
    public void setFirstInstall(boolean firstInstall) {
        mPrefs.edit().putBoolean(PREF_KEY_IS_FIRST_INSTALL, firstInstall).apply();
    }

    @Override
    public Boolean isUserLogin() {
        return mPrefs.getBoolean(PREF_KEY_IS_USER_LOGIN, false);
    }

    @Override
    public void setUserLogIn(boolean userLogIn) {
        mPrefs.edit().putBoolean(PREF_KEY_IS_USER_LOGIN, userLogIn).apply();
    }
}