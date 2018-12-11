package app.valai.ecart.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * @author by Mohit Arora on 26/8/18.
 * @projectname ECartApp
 */
public final class MarshmallowPermissions {

    public static boolean checkPermission(Context ctx, String permission) {
        int result = ContextCompat.checkSelfPermission(ctx, permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    public static void requestPermissions(final Context ctx, String[] permission, int requestPermissionCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) ctx, permission[0])) {
            ActivityCompat.requestPermissions((Activity) ctx, permission, requestPermissionCode);
        } else {
            ActivityCompat.requestPermissions((Activity) ctx, permission, requestPermissionCode);
        }
    }
}