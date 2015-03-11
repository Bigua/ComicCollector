package me.bigua.comiccollector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.*;
import android.widget.EditText;
import me.bigua.comiccollector.AbstBase.comicHandler;
import me.bigua.comiccollector.Models.Comic;

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
//
//
//        if (StringUtils.isBlank(comic_title.getText())) {
//            comic_title.setError("campo obrigatório");
//            return;
//        } else {
//            raw.put("title", comic_title.getText().toString().trim());
//        }


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

        this.saveComic(view,raw);

    }

    public void saveComic(View view, Map<String, String> raw) {
//        Log.wtf("bruto", raw.toString());

//        Comic comic = new Comic(brute.get(title) "teste", 11, "capa", "marvel", "barras", 01);
//        Comic comic2 = new Comic("te2", 14, "ca", "mel", "bs", 02);
        Comic comic = new Comic();
        comicHandler ch = new comicHandler(view.getContext());
        Long id = ch.insertComic(comic);
        Log.wtf("id inserido", String.valueOf(id));
//        ch.insertComic(comic2);
//        ch.listComics();
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
