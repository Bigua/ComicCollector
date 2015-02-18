package me.bigua.comiccollector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
        Button button = (Button) view.findViewById(R.id.save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do something
                getValues((Button) v);
            }
        });
        return view;
    }

    public void getValues(View view) {
        Map<String, String> brute = new HashMap<>();
        EditText title = (EditText) view.findViewById(R.id.comic_title);
         Log.wtf("lala", title.getText().toString());
//        String strtitle = title.getText().toString();
//        if (TextUtils.isEmpty(strtitle)) {
//            title.setError("vazio");
//            return;
//        } else {
//            brute.put("title", title.getText().toString());
//        }


//        TextView number = (TextView) view.findViewById(R.id.number);
//        brute.put("number", number.getText().toString());
//
//        TextView author = (TextView) view.findViewById(R.id.author);
//        brute.put("author", author.getText().toString());
//
//        TextView year = (TextView) view.findViewById(R.id.year);
//        brute.put("year", year.getText().toString());
//
//        TextView publisher = (TextView) view.findViewById(R.id.publisher);
//        brute.put("publisher", publisher.getText().toString());

        this.saveComic(brute);

    }

    public void saveComic(Map<String, String> brute) {
        Log.wtf("bruto", brute.toString());

//        Comic comic = new Comic(brute.get(title) "teste", 11, "capa", "marvel", "barras", 01);
//        Comic comic2 = new Comic("te2", 14, "ca", "mel", "bs", 02);
//        comicHandler ch = new comicHandler(view.getContext());
//        ch.insertComic(comic);
//        ch.insertComic(comic2);
//        ch.listComics();
    }
}
