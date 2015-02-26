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
public class ListAdapter extends ArrayAdapter<Comic> {

    Context context;


    public ListAdapter(Context context, int resource, List<Comic> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
//        LayoutInflater vi = (LayoutInflater) getContext().
//                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
        convertView = vi.inflate(R.layout.item_list, null);

        return convertView;
    }

}
