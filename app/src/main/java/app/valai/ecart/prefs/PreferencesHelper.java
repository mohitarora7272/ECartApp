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

/**
 * @author by Mohit Arora on 26/8/18.
 * @projectname ECartApp
 */
public interface PreferencesHelper {
    Boolean isFirstInstall();

    void setFirstInstall(boolean firstInstall);

    Boolean isUserLogin();

    void setUserLogIn(boolean userLogIn);
}