package me.bigua.comiccollector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CoverFragment extends Fragment implements AsyncDelegate, AdapterView.OnItemClickListener {

    private static final String ARG_SECTION_NUMBER = "section_number";
    public int oldId = -1;
    CoverAdapter coverAdapter;
    GridView grid_images;
    private ArrayList<UrlBundler> listImages = new ArrayList<UrlBundler>();

    public static CoverFragment newInstance(int sectionNumber) {
        CoverFragment fragment = new CoverFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cover, container, false);
        grid_images = (GridView) view.findViewById(R.id.grid_image);

        GetImages GetImages = new GetImages(view.getContext());
        GetImages.setOnCompleteListener(this);

        GetImages GetImages2 = new GetImages(view.getContext());
        GetImages2.setOnCompleteListener(this);

        Bundle bundle = this.getArguments();

        GetImages.execute(String.valueOf(bundle.get("param1")));
        GetImages2.execute(String.valueOf(bundle.get("param2")));

        coverAdapter = new CoverAdapter(getActivity(), R.layout.cover_list, listImages);
        grid_images.setAdapter(coverAdapter);
        grid_images.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void asyncComplete(Object result) {
        for (String url : (ArrayList<String>) result) {
            UrlBundler urlBundler = new UrlBundler(url, Boolean.FALSE);
            this.coverAdapter.add(urlBundler);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView HiddenUrl = (TextView) view.findViewById(R.id.hidden_url);
        ((MainActivity) view.getContext()).putInBundle("url", (String) HiddenUrl.getText());

        if (oldId != -1) {
            listImages.get(oldId).setSelected(Boolean.FALSE);
            view = parent.getChildAt(oldId);
            ImageView oldCheck = (ImageView) view.findViewById(R.id.check);
            oldCheck.setVisibility(View.GONE);
        }

        listImages.get(position).setSelected(Boolean.TRUE);
        ImageView check = (ImageView) parent.findViewById(R.id.check);
        check.setVisibility(View.VISIBLE);
        coverAdapter.notifyDataChanged();
        oldId = position;

    }


}

