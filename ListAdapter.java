package me.bigua.comiccollector;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import me.bigua.comiccollector.AbstBase.Models.Comic;

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
        if (rowView == null) {
            rowView = vi.inflate(R.layout.item_list, parent, false);
            ViewHolder vh = new ViewHolder();
            vh.name = (TextView) rowView.findViewById(R.id.nameLine);
            vh.number = (TextView) rowView.findViewById(R.id.numberLine);
            vh.img = (ImageView) rowView.findViewById(R.id.icon);

            Comic comic = objects.get(position);
            if (comic.getCover() != null) {

                Picasso.with(this.context).load(Integer.parseInt(comic.getCover())).resize(120, 200).into(vh.img);

            }
            vh.name.setText(comic.getName());
            if (comic.getCover() != null) {
                vh.number.setText("#" + comic.getNumber());
            }
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
