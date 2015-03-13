package me.bigua.comiccollector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.*;
import android.view.animation.AlphaAnimation;
import android.widget.*;
import me.bigua.comiccollector.AbstBase.DataProxy;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bigua on 2/10/15.
 * bigua.kun@gmail.com
 */

public class AddFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_SECTION_NUMBER = "section_number";


    private EditText title;
    private EditText num;
    private EditText author;
    private EditText year;
    private EditText publi;
    private EditText lang;
    private EditText type;
    private Switch wish;
    private TextView fields;
    private LinearLayout layout;
    private ScrollView scroll;

    public static AddFragment newInstance(int sectionNumber) {
        AddFragment fragment = new AddFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_add,
                container, false);

        title = (EditText) view.findViewById(R.id.comic_title);
        num = (EditText) view.findViewById(R.id.num_comic);
        author = (EditText) view.findViewById(R.id.author);
        year = (EditText) view.findViewById(R.id.year);
        publi = (EditText) view.findViewById(R.id.publisher);
        lang = (EditText) view.findViewById(R.id.lang);
        type = (EditText) view.findViewById(R.id.type);
        wish = (Switch) view.findViewById(R.id.wish_list);
        fields = (TextView) view.findViewById(R.id.fields_add);
        scroll = (ScrollView) view.findViewById(R.id.scrollView);
        layout = (LinearLayout) view.findViewById(R.id.more_fields);
        fields.setOnClickListener(this);

        setHasOptionsMenu(true);

        ((MainActivity) getActivity())
                .setActionBarTitle(R.string.add_comic);

        return view;
    }

    public void getValues(View view) {


        Map<String, String> raw = new HashMap<>();
        if (wish.isChecked()) {
            raw.put("wishlist", "true");
        }

        if (StringUtils.isBlank(title.getText())) {
            title.setError(getText(R.string.not_empty));
            return;
        } else {
            raw.put("title", title.getText().toString().trim());
        }

        if (StringUtils.isNotBlank(num.getText())) {
            raw.put("number", num.getText().toString().trim());
        }

        if (StringUtils.isNotBlank(author.getText())) {
            raw.put("author", author.getText().toString().trim());
        }

        if (StringUtils.isNotBlank(year.getText())) {
            raw.put("year", year.getText().toString().trim());
        }
        if (StringUtils.isNotBlank(publi.getText())) {
            raw.put("publi", publi.getText().toString().trim());
        }

        if (StringUtils.isNotBlank(lang.getText())) {
            raw.put("lang", lang.getText().toString().trim());
        }

        if (StringUtils.isNotBlank(type.getText())) {
            raw.put("type", type.getText().toString().trim());
        }

        this.saveComic(view, raw);

    }

    public void saveComic(View view, Map<String, String> raw) {
        DataProxy dataproxy = new DataProxy(view.getContext());
        dataproxy.saveComic(raw);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);

        final MenuItem settings = menu.findItem(R.id.menu_settings);
        settings.setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_add:
//                Toast.makeText(getActivity(), "botao ", Toast.LENGTH_SHORT).show();
                this.getValues(getView());

                break;

            case R.id.menu_settings:
//                mSearchCheck = true;
//                Toast.makeText(getActivity(), R.string.search, Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fields_add:
                fields.setVisibility(View.GONE);
                AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
                fadeIn.setDuration(1200);
                fadeIn.setFillAfter(true);
                layout.setVisibility(View.VISIBLE);
                layout.startAnimation(fadeIn);

                scroll.post(new Runnable() {

                    @Override
                    public void run() {
                        scroll.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                });

                break;
        }
    }

}

