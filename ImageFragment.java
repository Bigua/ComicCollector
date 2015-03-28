package me.bigua.comiccollector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

public class ImageFragment extends Fragment implements AsyncDelegate {

    private static final String ARG_SECTION_NUMBER = "section_number";
    ImageAdapter imageAdapter;
    private ArrayList<String> listImages = new ArrayList<String>();

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

        GetImages GetImages2 = new GetImages(view.getContext());
        GetImages2.setOnCompleteListener(this);

        Bundle bundle = this.getArguments();

        GetImages.execute(String.valueOf(bundle.get("param1")));
        GetImages2.execute(String.valueOf(bundle.get("param2")));

        imageAdapter = new ImageAdapter(getActivity(), R.layout.image_list, listImages);
        grid_images.setAdapter(imageAdapter);
        return view;
    }

    @Override
    public void asyncComplete(Object result) {
        for (String url : (ArrayList<String>) result)
            this.imageAdapter.add(url);
    }
}

