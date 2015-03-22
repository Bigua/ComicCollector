package me.bigua.comiccollector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import me.bigua.comiccollector.AbstBase.Handlers.comicHandler;

/**
 * Created by Bigua on 2/17/15.
 * bigua.kun@gmail.com
 */
public class ListFragment extends Fragment {


    private static final String ARG_SECTION_NUMBER = "section_number";

    private ListView listView;
    private ListAdapter listAdapter;
    private comicHandler comicHandler;

    public static ListFragment newInstance(int sectionNumber) {
        ListFragment fragment = new ListFragment();
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
                R.layout.fragment_list,
                container, false);

        listView = (ListView) view.findViewById(R.id.listView);
        comicHandler = new comicHandler(this.getActivity());
        listAdapter = new ListAdapter(getActivity().getBaseContext(), R.layout.item_list, comicHandler.List());
        listView.setAdapter(listAdapter);
        ((MainActivity) getActivity()).setActionBarTitle(R.string.list_comics);

        return view;
    }
}
