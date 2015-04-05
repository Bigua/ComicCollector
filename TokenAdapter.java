package me.bigua.comiccollector;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tokenautocomplete.FilteredArrayAdapter;
import me.bigua.comiccollector.AbstBase.Models.Author;

import java.util.List;

/**
 * Created by Bigua on 4/5/15.
 * bigua.kun@gmail.com
 */
public class TokenAdapter extends FilteredArrayAdapter<Author> {
    public TokenAdapter(Context context, int resource, List<Author> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater l = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = l.inflate(R.layout.author_layout, parent, false);
        }
        Author p = getItem(position);
        ((TextView) convertView.findViewById(R.id.author_token)).setText(p.getName());
        return convertView;
    }

    @Override
    protected boolean keepObject(Author author, String mask) {
        mask = mask.toLowerCase();
        return author.getName().toLowerCase().startsWith(mask);
    }
}
