package me.bigua.comiccollector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class ImageFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    IEventListener onGetResultComplete = new IEventListener() {
        @Override
        public void onEventFired(Object result) {
            //Aqui vem o seu código do retorno
            Object a = result;
            Log.wtf("voltou",a.toString());
        }
    };

    private View view;
    private GridView grid_images;
    private ImageAdapter resultsImageAdapter;

    public static ImageFragment newInstance(int sectionNumber) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_image, container, false);
        Bundle bundle = this.getArguments();
        String lol = bundle.getString("lalala");
        Log.w("funcionou", lol);

        grid_images = (GridView) view.findViewById(R.id.grid_image);


        GetImages GetImages = new GetImages(view.getContext());
        GetImages.setOnCompleteListener(onGetResultComplete);
        GetImages.execute("eu");

        return view;
    }
}
