package app.valai.ecart.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import app.valai.ecart.R;
import app.valai.ecart.fragments.AboutUsFragment;
import app.valai.ecart.fragments.CategoriesFragment;
import app.valai.ecart.fragments.CheckOutFragment;
import app.valai.ecart.fragments.ContactUsFragment;
import app.valai.ecart.fragments.FAQsFragment;
import app.valai.ecart.fragments.HomeFragment;
import app.valai.ecart.fragments.MyCartFragment;
import app.valai.ecart.fragments.MyOrderFragment;
import app.valai.ecart.fragments.MyProfileFragment;
import app.valai.ecart.fragments.NewProductsFragment;
import app.valai.ecart.fragments.OffersFragment;
import app.valai.ecart.fragments.PopularProductsFragment;
import app.valai.ecart.fragments.ProductsFragment;
import app.valai.ecart.fragments.SearchFragment;
import app.valai.ecart.fragments.SettingsFragment;
import app.valai.ecart.fragments.WeeklySpecialsFragment;
import app.valai.ecart.interfaces.MainMvpView;
import app.valai.ecart.utils.AppConstants;
import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static app.valai.ecart.utils.AppConstants.DELAY_TIME_OUT;
import static app.valai.ecart.utils.AppConstants.TAG_ABOUT_US;
import static app.valai.ecart.utils.AppConstants.TAG_CATEGORIES;
import static app.valai.ecart.utils.AppConstants.TAG_CHECK_OUT;
import static app.valai.ecart.utils.AppConstants.TAG_CONTACT_US;
import static app.valai.ecart.utils.AppConstants.TAG_FAQS;
import static app.valai.ecart.utils.AppConstants.TAG_HOME;
import static app.valai.ecart.utils.AppConstants.TAG_MY_CART;
import static app.valai.ecart.utils.AppConstants.TAG_MY_ORDER;
import static app.valai.ecart.utils.AppConstants.TAG_MY_PROFILE;
import static app.valai.ecart.utils.AppConstants.TAG_NEW_PRODUCTS;
import static app.valai.ecart.utils.AppConstants.TAG_OFFERS;
import static app.valai.ecart.utils.AppConstants.TAG_POPULAR_PRODUCTS;
import static app.valai.ecart.utils.AppConstants.TAG_PRODUCTS;
import static app.valai.ecart.utils.AppConstants.TAG_SEARCH;
import static app.valai.ecart.utils.AppConstants.TAG_SETTINGS;
import static app.valai.ecart.utils.AppConstants.TAG_WEEKLY_SPECIALS;

/**
 * @author by Mohit Arora on 22/8/18.
 * @projectname ECartApp
 */
public class MainActivity extends BaseActivity implements MainMvpView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer_layout;

    @BindView(R.id.nav_view)
    NavigationView nav_view;

    private int navItemIndex = 0;
    private String CURRENT_TAG = TAG_HOME;
    private String[] activityTitles;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUnBinder(ButterKnife.bind(this));
        setSupportActionBar(toolbar);
        mHandler = new Handler();
        View navHeader = nav_view.getHeaderView(0);
        TextView txtName = navHeader.findViewById(R.id.name);
        TextView email = navHeader.findViewById(R.id.email);
        final ImageView imgLogo = navHeader.findViewById(R.id.img_logo);
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        // Initializing Navigation Menu
        setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem menuItemSearch = menu.findItem(R.id.search);
        MenuItem menuItemCart = menu.findItem(R.id.cart);

        if (CURRENT_TAG.equals(TAG_SEARCH)) {
            hideMenuItem(menuItemSearch);
            showMenuItem(menuItemCart);
            return true;
        }

        if (CURRENT_TAG.equals(TAG_MY_CART)) {
            hideMenuItem(menuItemCart);
            showMenuItem(menuItemSearch);
            return true;
        }

        showMenuItem(menuItemCart);
        showMenuItem(menuItemSearch);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        Drawable drawable = item.getIcon();
//        if (drawable instanceof Animatable) {
//            ((Animatable) drawable).start();
//        }
        switch (item.getItemId()) {
            case R.id.search:
                navItemIndex = 8;
                CURRENT_TAG = TAG_SEARCH;
                loadHomeFragment();
                return true;

            case R.id.cart:
                navItemIndex = 10;
                CURRENT_TAG = TAG_MY_CART;
                loadHomeFragment();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*
     * Returns respected fragment that user
     * selected from navigation menu
     */
    private void loadHomeFragment() {
        selectNavMenu();
        setToolbarTitle();
        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer_layout.closeDrawers();
            return;
        }

        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        mHandler.postDelayed(mPendingRunnable, DELAY_TIME_OUT);
        drawer_layout.closeDrawers();
        invalidateOptionsMenu();
    }

    @NonNull
    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                return new HomeFragment();
            case 1:
                return new MyProfileFragment();
            case 2:
                return new WeeklySpecialsFragment();
            case 3:
                return new OffersFragment();
            case 4:
                return new NewProductsFragment();
            case 5:
                return new PopularProductsFragment();
            case 6:
                return new CategoriesFragment();
            case 7:
                return new ProductsFragment();
            case 8:
                return new SearchFragment();
            case 9:
                return new MyOrderFragment();
            case 10:
                return new MyCartFragment();
            case 11:
                return new CheckOutFragment();
            case 12:
                return new SettingsFragment();
            case 15:
                return new AboutUsFragment();
            case 16:
                return new ContactUsFragment();
            case 17:
                return new FAQsFragment();
            default:
                return new HomeFragment();
        }
    }

    private void setToolbarTitle() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(activityTitles[navItemIndex]);
        }
    }

    private void selectNavMenu() {
        nav_view.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        break;
                    case R.id.nav_my_profile:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_MY_PROFILE;
                        break;
                    case R.id.nav_weekly_specials:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_WEEKLY_SPECIALS;
                        break;
                    case R.id.nav_offers:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_OFFERS;
                        break;
                    case R.id.nav_new_products:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_NEW_PRODUCTS;
                        break;
                    case R.id.nav_popular_products:
                        navItemIndex = 5;
                        CURRENT_TAG = TAG_POPULAR_PRODUCTS;
                        break;
                    case R.id.nav_categories:
                        navItemIndex = 6;
                        CURRENT_TAG = TAG_CATEGORIES;
                        break;
                    case R.id.nav_products:
                        navItemIndex = 7;
                        CURRENT_TAG = TAG_PRODUCTS;
                        break;
                    case R.id.nav_search:
                        navItemIndex = 8;
                        CURRENT_TAG = TAG_SEARCH;
                        break;
                    case R.id.nav_my_order:
                        navItemIndex = 9;
                        CURRENT_TAG = TAG_MY_ORDER;
                        break;
                    case R.id.nav_my_cart:
                        navItemIndex = 10;
                        CURRENT_TAG = TAG_MY_CART;
                        break;
                    case R.id.nav_check_out:
                        if (!getAppPreferenceHelper().isUserLogin()) {
                            drawer_layout.closeDrawers();
                            openSignInActivity();
                            return false;
                        }
                        navItemIndex = 11;
                        CURRENT_TAG = TAG_CHECK_OUT;
                        break;
                    case R.id.nav_settings:
                        navItemIndex = 12;
                        CURRENT_TAG = TAG_SETTINGS;
                        break;
                    case R.id.nav_rate_us:
                        navItemIndex = 13;
                        drawer_layout.closeDrawers();
                        rateUs();
                        break;
                    case R.id.nav_share:
                        navItemIndex = 14;
                        drawer_layout.closeDrawers();
                        shareApp();
                        break;
                    case R.id.nav_about_us:
                        navItemIndex = 15;
                        CURRENT_TAG = TAG_ABOUT_US;
                        break;
                    case R.id.nav_contact_us:
                        navItemIndex = 16;
                        CURRENT_TAG = TAG_CONTACT_US;
                        break;
                    case R.id.nav_faqs:
                        navItemIndex = 17;
                        CURRENT_TAG = TAG_FAQS;
                        break;
                    case R.id.nav_log_out:
                        navItemIndex = 18;
                        drawer_layout.closeDrawers();
                        logOut();
                        break;
                    default:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                }

                if (navItemIndex == 13 || navItemIndex == 14 || navItemIndex == 18) {
                    drawer_layout.closeDrawers();
                    return false;
                }

                loadHomeFragment();
                return true;
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                hideKeyboard();
            }
        };

        drawer_layout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void rateUs() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + this.getPackageName())));
    }

    @Override
    public void shareApp() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.app_name));
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, AppConstants.shareLink + this.getPackageName());
        startActivity(Intent.createChooser(sharingIntent, "Share Via"));
    }

    @Override
    public void logOut() {
//        Intent intent = new Intent(this, IntroductionActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
//        finish();
    }

    @Override
    public void openSignInActivity() {
        Intent intent = new Intent(this, SignInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void openFragment(Integer index, String tag) {
        navItemIndex = index;
        CURRENT_TAG = tag;
        loadHomeFragment();
    }

    @Override
    public void onFragmentAttached() {
    }

    @Override
    public void onFragmentDetached(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment != null) {
            fragmentManager.beginTransaction().disallowAddToBackStack()
                    .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
                    .remove(fragment)
                    .commitAllowingStateLoss();
            unlockDrawer();
        }
    }

    @Override
    public void lockDrawer() {
        if (drawer_layout != null)
            drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    @Override
    public void unlockDrawer() {
        if (drawer_layout != null)
            drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    @Override
    public void showMenuItem(MenuItem item) {
        if (item != null) {
            item.setVisible(true);
        }
    }

    @Override
    public void hideMenuItem(MenuItem item) {
        if (item != null) {
            item.setVisible(false);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawers();
            return;
        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        // checking if user is on other navigation menu
        // rather than home
        if (navItemIndex != 0) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}