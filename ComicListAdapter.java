package me.bigua.comiccollector;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import me.bigua.comiccollector.Models.Comic;

import java.util.List;

/**
 * Created by Bigua on 2/20/15.
 * bigua.kun@gmail.com
 */
public class ComicListAdapter extends ArrayAdapter<Comic> {


    public ComicListAdapter(Context context, int resource, List<Comic> objects) {
        super(context, resource, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater vi = (LayoutInflater) getContext().
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        return convertView;
    }

}
