package me.bigua.comiccollector.Utils;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tokenautocomplete.TokenCompleteTextView;
import me.bigua.comiccollector.AbstBase.Models.Author;
import me.bigua.comiccollector.R;

/**
 * Created by Bigua on 4/3/15.
 * bigua.kun@gmail.com
 */
public class AuthorCompletionView extends TokenCompleteTextView {

    public AuthorCompletionView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected View getViewForObject(Object name) {
        Author author = (Author) name;
        LayoutInflater l = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        LinearLayout view = (LinearLayout) l.inflate(R.layout.author_token, (ViewGroup) AuthorCompletionView.this.getParent(), false);
        ((TextView) view.findViewById(R.id.author_name)).setText(author.getName());
        return view;
    }

    @Override
    protected Object defaultObject(String s) {
        return new Author(s);
    }
}
