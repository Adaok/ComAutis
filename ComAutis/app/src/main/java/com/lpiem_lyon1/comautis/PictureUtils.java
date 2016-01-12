package com.lpiem_lyon1.comautis;

import android.os.Environment;

import java.io.File;
import java.io.IOException;

/**
 * Created by alexislp on 12/01/16.
 */
public class PictureUtils {

    public static void createPictureDirectory(){
        String folder_pictures = "ComAutisPictures";

        //File dir = new File(Environment.DIRECTORY_PICTURES, folder_pictures);
        //if (!dir.exists()){
        //    dir.mkdir();
        //}

        File directory = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"ComAutis");
        directory.mkdirs();
        directory.setExecutable(true);
        directory.setWritable(true);
        directory.setReadable(true);
    }

    public static File[] getPictures(){
        File directory = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"ComAutis");
        return directory.listFiles();
    }
}
