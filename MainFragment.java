package me.bigua.comiccollector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import me.bigua.comiccollector.AbstBase.comicHandler;
import me.bigua.comiccollector.Models.Comic;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_main,
                container, false);
        Button button = (Button) view.findViewById(R.id.save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do something
                getValues(v);
            }
        });
        return view;
    }

    public void getValues(View view) {
//        TextView title = (TextView) view.findViewById(R.id.title);
        Comic comic = new Comic("teste", 11, "capa", "marvel", "barras", 01);
        Comic comic2 = new Comic("te2", 14, "ca", "mel", "bs", 02);
        comicHandler ch = new comicHandler(view.getContext());
        ch.insertComic(comic);
        ch.insertComic(comic2);
        ch.listComics();

    }


}
