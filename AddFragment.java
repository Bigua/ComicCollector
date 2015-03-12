package me.bigua.comiccollector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.*;
import android.widget.EditText;
import me.bigua.comiccollector.AbstBase.DataProxy;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bigua on 2/10/15.
 * bigua.kun@gmail.com
 */

public class AddFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";


    private EditText comic_title;
    private EditText num_comic;
    private EditText author;
    private EditText year;
    private EditText publisher;
    private EditText lang;
    private EditText type;

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

        comic_title = (EditText) view.findViewById(R.id.comic_title);
        num_comic = (EditText) view.findViewById(R.id.num_comic);
        author = (EditText) view.findViewById(R.id.author);
        year = (EditText) view.findViewById(R.id.year);
        publisher = (EditText) view.findViewById(R.id.publisher);
        lang = (EditText) view.findViewById(R.id.lang);
        type = (EditText) view.findViewById(R.id.type);

        setHasOptionsMenu(true);

        ((MainActivity) getActivity())
                .setActionBarTitle(R.string.add_comic);

        return view;
    }

    public void getValues(View view) {


        Map<String, String> raw = new HashMap<>();

        if (StringUtils.isBlank(comic_title.getText())) {
            comic_title.setError(getText(R.string.not_empty));
            return;
        } else {
            raw.put("title", comic_title.getText().toString().trim());
        }

        if (StringUtils.isNotBlank(num_comic.getText())) {
            raw.put("number", num_comic.getText().toString().trim());
        }

        if (StringUtils.isNotBlank(author.getText())) {
            raw.put("author", author.getText().toString().trim());
        }

        if (StringUtils.isNotBlank(year.getText())) {
            raw.put("year", year.getText().toString().trim());
        }
        if (StringUtils.isNotBlank(publisher.getText())) {
            raw.put("publisher", publisher.getText().toString().trim());
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
}
