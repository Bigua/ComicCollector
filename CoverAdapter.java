package me.bigua.comiccollector;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Bigua on 3/23/15.
 * bigua.kun@gmail.com
 */
public class CoverAdapter extends ArrayAdapter<UrlMixer> {

    private Context context;
    private List<UrlMixer> objects;

    public CoverAdapter(Context context, int resource, List<UrlMixer> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;

    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View GridItem, ViewGroup parent) {
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder vh;
        if (GridItem == null) {
            GridItem = vi.inflate(R.layout.cover_list, parent, false);
            vh = new ViewHolder();
            vh.wrap = (RelativeLayout) GridItem.findViewById(R.id.cover_wrap);
            vh.image = (ImageView) GridItem.findViewById(R.id.cover_image);
            vh.check = (ImageView) GridItem.findViewById(R.id.check);
            vh.hiddenUrl = (TextView) GridItem.findViewById(R.id.hidden_url);
            GridItem.setTag(vh);
        } else {
            vh = (ViewHolder) GridItem.getTag();
        }
        Picasso.with(this.context).load(objects.get(position).getUrl()).fit().into(vh.image);
        //vh.check.setVisibility(View.GONE);

        vh.hiddenUrl.setText(objects.get(position).getUrl());
        if (objects.get(position).isSelected()) {
            vh.check.setVisibility(View.VISIBLE);
        } else {
            vh.check.setVisibility(View.GONE);
        }
        //GridItem.setOnClickListener(this);
//        vh.image.setOnClickListener(this);
//        vh.check.setOnClickListener(this);
        //vh.wrap.setOnClickListener(this);
        return GridItem;
    }

    public void notifyDataChanged() {
        super.notifyDataSetChanged();
    }

    //   @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.cover_image:
//                Log.wtf("cover", "click");
//                break;
//            case R.id.check:
//                Log.wtf("check", "click");
//                break;
//            case R.id.cover_wrap:
//                Log.wtf("wrap", "click");
//                break;
//
//        }

    //      ImageView im = (ImageView) v.findViewById(R.id.check);
    //     im.setVisibility(View.VISIBLE);
//            v.setSelected(true);
//        TextView hiddenUrl = (TextView) v.findViewById(R.id.hidden_url);
//        this.context.getApplicationContext();
//        ((MainActivity) this.context).putInBundle("url", (String) hiddenUrl.getText());
    //   }

    /*private view holder class*/
    class ViewHolder {
        RelativeLayout wrap;
        ImageView image;
        ImageView check;
        TextView hiddenUrl;
    }
}