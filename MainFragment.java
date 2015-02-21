package me.bigua.comiccollector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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


        Button button = (Button) view.findViewById(R.id.save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValues(v);
            }
        });
        return view;
    }

    public void getValues(View v) {
        Map<String, String> raw = new HashMap<>();
        String strtitle = comic_title.getText().toString().trim();
        if (TextUtils.isEmpty(strtitle)) {
            comic_title.setError("campo vazio");
            return;
        } else {
            raw.put("title", comic_title.getText().toString());
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
}
