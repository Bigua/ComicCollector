package me.bigua.comiccollector.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import me.bigua.comiccollector.AbstBase.Models.Comic;
import me.bigua.comiccollector.R;

import java.io.File;
import java.util.List;

/**
 * Created by Bigua on 2/20/15.
 * bigua.kun@gmail.com
 */
public class ListAdapter extends ArrayAdapter<Comic> {

    private Context context;
    private List<Comic> objects;

    public ListAdapter(Context context, int resource, List<Comic> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View rowView, ViewGroup parent) {
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Comic comic = objects.get(position);
        ViewHolder vh;
        if (rowView == null) {
            rowView = vi.inflate(R.layout.item_list, parent, false);
            vh = new ViewHolder();
            vh.name = (TextView) rowView.findViewById(R.id.nameView);
            vh.number = (TextView) rowView.findViewById(R.id.numberView);
            vh.img = (ImageView) rowView.findViewById(R.id.icon);
            rowView.setTag(vh);
        } else {
            vh = (ViewHolder) rowView.getTag();
        }
        if (comic.getCover() != null) {
            File f = new File(comic.getCover());
            if (f.exists()) {
                Picasso.with(this.context).load(f).fit().centerCrop().into(vh.img);
            } else {
                Picasso.with(this.context).load((comic.getCover())).fit().centerCrop().into(vh.img);
            }
        }
        vh.name.setText(comic.getName());
        if (comic.getNumber() != null) {
            vh.number.setText("#" + comic.getNumber());
        }
        return rowView;
    }

    /*private view holder class*/
    private class ViewHolder {
        TextView name;
        TextView number;
        ImageView img;
    }
}
