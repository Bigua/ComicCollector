package me.bigua.comiccollector;

import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Bigua on 2/5/15.
 * bigua.kun@gmail.com
 */
public class DealWithFiles {

    public DealWithFiles() {
    }


    public String saveBitmap(String filename, Bitmap bmp) {
        String folderPath = Environment.getExternalStorageDirectory() + "/ComicCollector";
        File folder = new File(folderPath);
        String filepath = "";
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdir();
        }
        if (success) {
            FileOutputStream outStream = null;
            File file = new File(folderPath, filename + ".png");
            if (file.exists()) {
                file.delete();
                file = new File(folderPath, filename + ".png");
            }
            try {
                outStream = new FileOutputStream(file);
                bmp.compress(Bitmap.CompressFormat.PNG, 100, outStream);
                outStream.flush();
                outStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return file.toString();
        }
        return filepath;
    }
}
