package com.coreproc.android.kitchen;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.text.format.DateUtils;
import android.util.Base64;

import com.kosalgeek.android.photoutil.PhotoLoader;

import org.ocpsoft.prettytime.PrettyTime;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by willm on 1/5/2017.
 */

public class Kitchen {

    public static String getRelativeTime(final Date date) {

        PrettyTime prettyTime = new PrettyTime();
        String dateStr = "" + prettyTime.format(date);
        dateStr = dateStr.contains("moments") ? "Just now" : dateStr;
        return dateStr;
    }

    public static String encodeImage(String path) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] b = null;
        Bitmap bm = null;
        try {
            bm = PhotoLoader.init().from(path).getBitmap();
            bm = Bitmap.createScaledBitmap(bm, 512, 512, false);
            bm = processImageOrientation(path, bm);
            bm.compress(Bitmap.CompressFormat.JPEG, 50, baos);
            bm.recycle();
            b = baos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return new String(Base64.encode(b, Base64.NO_WRAP));
    }

    public static Bitmap processImageOrientation(String photoPath, Bitmap bitmap) {
        try {
            ExifInterface ei = new ExifInterface(photoPath);
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_UNDEFINED);

            switch (orientation) {

                case ExifInterface.ORIENTATION_ROTATE_90:
                    return rotateImage(bitmap, 90);
                case ExifInterface.ORIENTATION_ROTATE_180:
                    return rotateImage(bitmap, 180);

                case ExifInterface.ORIENTATION_ROTATE_270:
                    return rotateImage(bitmap, 270);

                case ExifInterface.ORIENTATION_NORMAL:
                    return bitmap;
                default:
                    return bitmap;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }

}
