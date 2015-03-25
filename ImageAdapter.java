package me.bigua.comiccollector;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Bigua on 3/23/15.
 * bigua.kun@gmail.com
 */
public class ImageAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<String> objects;

    public ImageAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;

    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = vi.inflate(R.layout.image_list, parent,false);
        ImageView img = (ImageView) convertView.findViewById(R.id.image_ingrid);
        Picasso.with(this.context).load(objects.get(position)).resize(100, 150).into(img);
        return convertView;
    }


}