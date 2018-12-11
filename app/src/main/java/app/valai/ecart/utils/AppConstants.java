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

package app.valai.ecart.utils;
/**
 * @author by Mohit Arora on 26/8/18.
 * @projectname ECartApp
 */
public final class AppConstants {
    // Request API RESPONSE CODE
    public static final int RESPONSE_CODE = 200;

    // Splash screen timer
    public static final int SPLASH_TIME_OUT = 3000;

    // Menu Items Fragments Timer Delay
    public static final int DELAY_TIME_OUT = 250;

    // Notification Id Request Codes
    public static final int NOTIFICATION_ID = 100;
    public static final int NOTIFICATION_ID_BIG_IMAGE = 101;

    // Tags For Side Menu Items
    public static final String TAG_HOME = "Home";
    public static final String TAG_MY_PROFILE = "My Profile";
    public static final String TAG_WEEKLY_SPECIALS = "Weekly Specials";
    public static final String TAG_OFFERS = "Offers";
    public static final String TAG_NEW_PRODUCTS = "New Products";
    public static final String TAG_POPULAR_PRODUCTS = "Popular Products";
    public static final String TAG_CATEGORIES = "Categories";
    public static final String TAG_PRODUCTS = "Products";
    public static final String TAG_SEARCH = "Search";
    public static final String TAG_MY_ORDER = "My Order";
    public static final String TAG_MY_CART = "My Cart";
    public static final String TAG_CHECK_OUT = "Checkout";
    public static final String TAG_SETTINGS = "Settings";
    public static final String TAG_RATE_US = "Rate Us";
    public static final String TAG_SHARE = "Share";
    public static final String TAG_ABOUT_US = "About Us";
    public static final String TAG_CONTACT_US = "Contact Us";
    public static final String TAG_FAQS = "FAQ\\'s";
    public static final String TAG_LOGOUT = "Logout";

    // Share App Google Play Store Link
    public static final String shareLink = "https://play.google.com/store/apps/details?id=";

    // Preference Name
    public static final String PREF_NAME = "e_cart_pref";

    private AppConstants() {
        // This utility class is not publicly instantiable
    }
}