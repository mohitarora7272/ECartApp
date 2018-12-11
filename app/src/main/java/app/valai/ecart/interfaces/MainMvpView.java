package app.valai.ecart.interfaces;

import android.view.MenuItem;

/**
 * Base interface that any class that wants to act as a View in the MVP (Model View Presenter)
 * pattern must implement. Generally this interface will be extended by a more specific interface
 * that then usually will be implemented by an Activity or Fragment.
 */
public interface MainMvpView extends MvpView {
    void lockDrawer();

    void unlockDrawer();

    void showMenuItem(MenuItem item);

    void hideMenuItem(MenuItem item);

    void shareApp();

    void rateUs();

    void logOut();

    void openSignInActivity();

    void openFragment(Integer index, String tag);
}