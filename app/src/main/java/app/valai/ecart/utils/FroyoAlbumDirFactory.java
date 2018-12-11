package app.valai.ecart.utils;

import android.os.Environment;

import java.io.File;

/**
 * @author by Mohit Arora on 26/8/18.
 * @projectname ECartApp
 */
public final class FroyoAlbumDirFactory extends AlbumStorageDirFactory {

    @Override
    public File getAlbumStorageDir(String albumName) {
        // TODO Auto-generated method stub
        return new File(
                Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES
                ),
                albumName
        );
    }
}