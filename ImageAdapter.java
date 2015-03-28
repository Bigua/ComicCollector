package me.bigua.comiccollector;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Bigua on 3/23/15.
 * bigua.kun@gmail.com
 */
public class ImageAdapter extends ArrayAdapter<String> implements View.OnClickListener {

    private Context context;
    private List<String> objects;

    public ImageAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;

    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View GridItem, ViewGroup parent) {
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (GridItem == null) {
            GridItem = vi.inflate(R.layout.image_list, parent, false);
            ViewHolder vh = new ViewHolder();
            vh.image = (ImageView) GridItem.findViewById(R.id.image_ingrid);
            Picasso.with(this.context).load(objects.get(position)).fit().into(vh.image);
            GridItem.setTag(objects.get(position));
            GridItem.setOnClickListener(this);
        }
        return GridItem;
    }

    @Override
    public void onClick(View v) {

        GridView father = (GridView) v.getParent();

        for (int i = 0; i < father.getChildCount(); i++) {
            View z = father.getChildAt(i);
            z.setBackgroundColor(Color.TRANSPARENT);
        }
        v.setSelected(true);
        v.setBackgroundColor(Color.RED);
//        Log.wtf("url", String.valueOf(v.getTag()));
        this.context.getApplicationContext();
        ((MainActivity) this.context).putInBundle("url", String.valueOf(v.getTag()));
    }


    /*private view holder class*/
    private class ViewHolder {
        ImageView image;
    }
}