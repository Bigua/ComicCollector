package me.bigua.comiccollector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.*;
import android.widget.EditText;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bigua on 2/10/15.
 * bigua.kun@gmail.com
 */

public class MainFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";


    private EditText comic_title;
    private EditText num_comic;
    private EditText author;
    private EditText year;
    private EditText publisher;
    private EditText lang;
    private EditText type;

    public static MainFragment newInstance(int sectionNumber) {
        MainFragment fragment = new MainFragment();
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
                R.layout.fragment_main,
                container, false);

        comic_title = (EditText) view.findViewById(R.id.comic_title);
        num_comic = (EditText) view.findViewById(R.id.num_comic);
        author = (EditText) view.findViewById(R.id.author);
        year = (EditText) view.findViewById(R.id.year);
        publisher = (EditText) view.findViewById(R.id.publisher);
        lang = (EditText) view.findViewById(R.id.lang);
        type = (EditText) view.findViewById(R.id.type);

//
//        Button button = (Button) view.findViewById(R.id.save);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getValues(v);
//            }
//        });
        setHasOptionsMenu(true);
        return view;
    }

    public void getValues() {


        Map<String, String> raw = new HashMap<>();


        if (StringUtils.isBlank(comic_title.getText())) {
            comic_title.setError("campo vazio");
            return;
        } else {
            raw.put("title", comic_title.getText().toString().trim());
        }


//        TextView number = (TextView) view.findViewById(R.id.number);
//        raw.put("number", number.getText().toString());
//
//        TextView author = (TextView) view.findViewById(R.id.author);
//        raw.put("author", author.getText().toString());
//
//        TextView year = (TextView) view.findViewById(R.id.year);
//        raw.put("year", year.getText().toString());
//
//        TextView publisher = (TextView) view.findViewById(R.id.publisher);
//        raw.put("publisher", publisher.getText().toString());

        this.saveComic(raw);

    }

    public void saveComic(Map<String, String> raw) {
        Log.wtf("bruto", raw.toString());

//        Comic comic = new Comic(brute.get(title) "teste", 11, "capa", "marvel", "barras", 01);
//        Comic comic2 = new Comic("te2", 14, "ca", "mel", "bs", 02);
//        comicHandler ch = new comicHandler(view.getContext());
//        ch.insertComic(comic);
//        ch.insertComic(comic2);
//        ch.listComics();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();

        inflater.inflate(R.menu.menu_main, menu);


//        final MenuItem menuItem = menu.findItem(R.id.menu_add);
//        menuItem.setVisible(true);
        final MenuItem settings = menu.findItem(R.id.action_settings);
        settings.setVisible(false);
//        MenuItem item;
//        item = menu.add("lalala");
//        item.setIcon(R.drawable.abc_ic_go_search_api_mtrl_alpha);

        Log.wtf("chegou", "menu");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_add:
//                Toast.makeText(getActivity(), "botao ", Toast.LENGTH_SHORT).show();
                this.getValues();

                break;

            case R.id.action_settings:
//                mSearchCheck = true;
//                Toast.makeText(getActivity(), R.string.search, Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
