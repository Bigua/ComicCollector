package me.bigua.comiccollector.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by Bigua on 3/23/15.
 * bigua.kun@gmail.com
 */
public class GetImages extends AsyncTask<String, Void, Object> {

    JSONObject json;
    ArrayList<String> listImages = new ArrayList<String>();
    private Context context = null;
    private ProgressDialog progress;
    private AsyncDelegate<Object> onComplete;


    public GetImages(Context context) {
        this.context = context;
    }

    public void setOnCompleteListener(AsyncDelegate<Object> listener) {
        onComplete = listener;
    }

    protected void onPreExecute() {
        if (context != null) {
            progress = new ProgressDialog(context);
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setTitle("Carregando");
            progress.setMessage("Conectando ao servidor. Aguarde...");
            progress.setCancelable(false);
            progress.setIndeterminate(false);
            progress.setMax(300);
            progress.setProgress(0);
            progress.show();
        }
    }

    @Override
    protected Object doInBackground(String... params) {
        URL url;
        try {
            url = new URL("https://ajax.googleapis.com/ajax/services/search/images?" +
                    "v=1.0&q=" + params[0] + "&rsz=8"); //&key=ABQIAAAADxhJjHRvoeM2WF3nxP5rCBRcGWwHZ9XQzXD3SWg04vbBlJ3EWxR0b0NVPhZ4xmhQVm3uUBvvRF-VAA&userip=192.168.0.172");

            URLConnection connection = url.openConnection();
            connection.addRequestProperty("Referer", "http://technotalkative.com");

            String line;
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            json = new JSONObject(builder.toString());
        } catch (MalformedURLException e) {
            Log.wtf("MalformedURLException", "vish");
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            Log.wtf("IOException", "vish");
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            Log.wtf("JSONException", "vish");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object result) {
        try {
            JSONObject responseObject = json.getJSONObject("responseData");
            JSONArray resultArray = responseObject.getJSONArray("results");

            listImages = getImageList(resultArray);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (onComplete != null) {
            onComplete.asyncComplete(listImages);
        }

        if (context != null && progress != null) {
            progress.dismiss();
        }

    }

    public ArrayList<String> getImageList(JSONArray resultArray) {
        ArrayList<String> listImages = new ArrayList<String>();
        try {
            for (int i = 0; i < resultArray.length(); i++) {
                JSONObject obj;
                obj = resultArray.getJSONObject(i);
                listImages.add(obj.getString("url"));
            }
            return listImages;
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}