package app.valai.ecart.utils;

import java.io.File;

/**
 * @author by Mohit Arora on 26/8/18.
 * @projectname ECartApp
 */
public abstract class AlbumStorageDirFactory {
    public abstract File getAlbumStorageDir(String albumName);
}
