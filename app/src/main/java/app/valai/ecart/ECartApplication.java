package app.valai.ecart;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.jacksonandroidnetworking.JacksonParserFactory;

import okhttp3.OkHttpClient;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class ECartApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Adding an Network Interceptor for Debugging purpose :
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
        AndroidNetworking.initialize(getApplicationContext(), okHttpClient);
        // Then set the JacksonParserFactory like below
        AndroidNetworking.setParserFactory(new JacksonParserFactory());

        // Initialize Fonts
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/nova.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }
}