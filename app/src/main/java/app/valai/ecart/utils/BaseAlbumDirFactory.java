package app.valai.ecart.utils;

import android.os.Environment;

import java.io.File;

/**
 * @author by Mohit Arora on 26/8/18.
 * @projectname ECartApp
 */
public final class BaseAlbumDirFactory extends AlbumStorageDirFactory {

    // Standard storage location for digital camera files
    private static final String CAMERA_DIR = "/dcim/";

    @Override
    public File getAlbumStorageDir(String albumName) {
        return new File(
                Environment.getExternalStorageDirectory()
                        + CAMERA_DIR
                        + albumName
        );
    }
}
