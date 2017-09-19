package com.lpiem_lyon1.comautis;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Environment;

import java.io.File;

/**
 * Created by alexislp on 12/01/16.
 */
public class PictureUtils {

    public static void createPictureDirectory(){

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

    public static Bitmap getBitmapFromPath(String path){
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        bitmap = PictureUtils.getResizedBitmap(bitmap, 350, 350);
        return bitmap;
    }

    public static Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);
        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }
}
