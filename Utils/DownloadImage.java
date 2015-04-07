package me.bigua.comiccollector.Utils;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.net.URL;

/**
 * Created by Bigua on 3/29/15.
 * bigua.kun@gmail.com
 */
public class DownloadImage extends AsyncTask<String, Void, Object> {

    private Context context;
    private AsyncDelegate<Object> onComplete;

    public DownloadImage(Context context) {
        this.context = context;
    }

    public void setOnCompleteListener(AsyncDelegate<Object> listener) {
        onComplete = listener;
    }


    @Override
    protected Object doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            return BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object result) {
        if (onComplete != null) {
            onComplete.asyncComplete(result);
        }
    }
}