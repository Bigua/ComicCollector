package me.bigua.comiccollector;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by Bigua on 3/23/15.
 * bigua.kun@gmail.com
 */
public class ImageAdapter extends ArrayAdapter<String> {

    private Context context;


    public ImageAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.context = context;

    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {


        return null;
    }
}