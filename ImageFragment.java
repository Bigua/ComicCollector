package me.bigua.comiccollector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

public class ImageFragment extends Fragment implements AsyncDelegate {

    private static final String ARG_SECTION_NUMBER = "section_number";
    ImageAdapter imageAdapter;
    private ArrayList<String> listImages = new ArrayList<>();

    public static ImageFragment newInstance(int sectionNumber) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        GridView grid_images = (GridView) view.findViewById(R.id.grid_image);
        GetImages GetImages = new GetImages(view.getContext());
        GetImages.setOnCompleteListener(this);
        GetImages.execute("eu");
        Bundle bundle = this.getArguments();
        String lol = bundle.getString("lalala");
        Log.w("funcionou", lol);

        imageAdapter = new ImageAdapter(getActivity().getBaseContext(), R.layout.image_list, listImages);
        grid_images.setAdapter(imageAdapter);
        ((MainActivity) getActivity()).putInBundle("url", "lalala");
        return view;

//        grid_images.getSelectedItem();
    }

    @Override
    public void asyncComplete(Object result) {
        listImages = (ArrayList<String>) result;
        for (String url : listImages){
            this.imageAdapter.add(url);
        }
    }
}
