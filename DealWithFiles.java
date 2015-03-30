package me.bigua.comiccollector;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import java.io.File;

/**
 * Created by Bigua on 2/5/15.
 * bigua.kun@gmail.com
 */
public class DealWithFiles {

    private Context context;

    public DealWithFiles(Context context) {
        this.context = context;
    }


    public void downloadFile(String uRl, String name) {
        File direct = new File(Environment.getExternalStorageDirectory() + "/ComicCollector");
        if (!direct.exists()) {
            direct.mkdirs();
        }

        DownloadManager mgr = (DownloadManager) this.context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri downloadUri = Uri.parse(uRl);
        DownloadManager.Request request = new DownloadManager.Request(downloadUri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false).setTitle("Demo")
                .setDescription("Something useful. No, really.")
                .setDestinationInExternalPublicDir("/ComicCollector", name + "fileName.jpg");
        mgr.enqueue(request);
    }

//
//    public void DownloadFromUrl(String DownloadUrl, String fileName) {
//
//        try {
//            File root = android.os.Environment.getExternalStorageDirectory();
//
//            File dir = new File(root.getAbsolutePath() + "/xmls");
//            if (dir.exists() == false) {
//                dir.mkdirs();
//            }
//
//            URL url = new URL(DownloadUrl); //you can write here any link
//            File file = new File(dir, fileName);
//
//            long startTime = System.currentTimeMillis();
//            Log.d("DownloadManager", "download begining");
//            Log.d("DownloadManager", "download url:" + url);
//            Log.d("DownloadManager", "downloaded file name:" + fileName);
//
//           /* Open a connection to that URL. */
//            URLConnection ucon = url.openConnection();
//
//           /*
//            * Define InputStreams to read from the URLConnection.
//            */
//            InputStream is = ucon.getInputStream();
//            BufferedInputStream bis = new BufferedInputStream(is);
//
//           /*
//            * Read bytes to the Buffer until there is nothing more to read(-1).
//            */
//            ByteArrayBuffer baf = new ByteArrayBuffer(5000);
//            int current = 0;
//            while ((current = bis.read()) != -1) {
//                baf.append((byte) current);
//            }
//
//
//           /* Convert the Bytes read to a String. */
//            FileOutputStream fos = new FileOutputStream(file);
//            fos.write(baf.toByteArray());
//            fos.flush();
//            fos.close();
//            Log.d("DownloadManager", "download ready in" + ((System.currentTimeMillis() - startTime) / 1000) + " sec");
//
//        } catch (IOException e) {
//            Log.d("DownloadManager", "Error: " + e);
//        }
//    }


}
