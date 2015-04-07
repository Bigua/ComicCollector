package me.bigua.comiccollector.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import me.bigua.comiccollector.MainActivity;
import me.bigua.comiccollector.R;

public class WishListFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";


    public WishListFragment() {
        // Required empty public constructor
    }

    public static WishListFragment newInstance(int sectionNumber) {
        WishListFragment fragment = new WishListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wish_list, container, false);
        ((MainActivity) getActivity()).setActionBarTitle(R.string.whish_list);

        return view;

    }


}
