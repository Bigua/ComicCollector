package me.bigua.comiccollector;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.*;
import android.view.animation.AlphaAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import com.squareup.picasso.Picasso;
import me.bigua.comiccollector.AbstBase.DataProxy;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bigua on 2/10/15.
 * bigua.kun@gmail.com
 */

public class AddFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    File coverFile;
    private View view;
    private Switch wish;
    private EditText title;
    private EditText num;
    private EditText type;
    private EditText author;
    private Switch complete;
    private EditText galaxy;
    private EditText universe;
    private EditText publi;
    private EditText year;
    private EditText lang;
    private TextView fields;
    private ImageView cover;
    private String url;
    private LinearLayout layout;
    private ScrollView scroll;

    public static AddFragment newInstance(int sectionNumber) {
        AddFragment fragment = new AddFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add, container, false);

        title = (EditText) view.findViewById(R.id.comic_title);
        num = (EditText) view.findViewById(R.id.num_comic);
        author = (EditText) view.findViewById(R.id.author);
        year = (EditText) view.findViewById(R.id.year);
        publi = (EditText) view.findViewById(R.id.publisher);
        lang = (EditText) view.findViewById(R.id.lang);
        type = (EditText) view.findViewById(R.id.type);
        wish = (Switch) view.findViewById(R.id.wish_list);
        fields = (TextView) view.findViewById(R.id.fields_add);
        scroll = (ScrollView) view.findViewById(R.id.scrollView);
        layout = (LinearLayout) view.findViewById(R.id.more_fields);
        galaxy = (EditText) view.findViewById(R.id.galaxy);
        universe = (EditText) view.findViewById(R.id.universe);
        complete = (Switch) view.findViewById(R.id.complete);
        cover = (ImageView) view.findViewById(R.id.cover);
        Button from_internet = (Button) view.findViewById(R.id.from_internet);
        Button camera = (Button) view.findViewById(R.id.camera);

        from_internet.setOnClickListener(this);
        camera.setOnClickListener(this);
        fields.setOnClickListener(this);
        setHasOptionsMenu(true);
        ((MainActivity) getActivity()).setActionBarTitle(R.string.add_comic);
        return view;
    }

    public void getValues(View view) {

        Map<String, String> raw = new HashMap<String, String>();
        if (wish.isChecked()) {
            raw.put("wishlist", "true");
        }

        if (complete.isChecked()) {
            raw.put("complete", "true");
        }

        if (StringUtils.isBlank(title.getText())) {
            title.setError(getText(R.string.not_empty));
            return;
        } else {
            raw.put("name", title.getText().toString().trim());
        }

        if (StringUtils.isNotBlank(year.getText())) {
            raw.put("year", year.getText().toString().trim());
        }

        if (StringUtils.isNotBlank(num.getText())) {
            raw.put("number", num.getText().toString().trim());
        }

        if (StringUtils.isNotBlank(author.getText())) {
            raw.put("author", author.getText().toString().trim());
        }

        if (StringUtils.isNotBlank(publi.getText())) {
            raw.put("publi", publi.getText().toString().trim());
        }

        if (StringUtils.isNotBlank(lang.getText())) {
            raw.put("lang", lang.getText().toString().trim());
        }

        if (StringUtils.isNotBlank(type.getText())) {
            raw.put("type", type.getText().toString().trim());
        }

        if (StringUtils.isNotBlank(galaxy.getText())) {
            raw.put("galaxy", galaxy.getText().toString().trim());
        }

        if (StringUtils.isNotBlank(universe.getText())) {
            raw.put("universe", universe.getText().toString().trim());
        }

        if (this.url != null) {
            raw.put("cover", this.url);
        }
        this.saveComic(view, raw);
    }

    public void saveComic(View view, Map<String, String> raw) {
        DataProxy dataproxy = new DataProxy(view.getContext());
        dataproxy.persistComic(raw);
        Scroll(ScrollView.FOCUS_UP);
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

    @Override
    public void onClick(View v) {
        this.hideKeyboard();
        switch (v.getId()) {
            case R.id.fields_add:
                goodBye(fields);
                comeIn(layout);
                Scroll(ScrollView.FOCUS_DOWN);
                break;
            case R.id.from_internet:
                if (StringUtils.isNotBlank(title.getText()) && StringUtils.isNotBlank(type.getText())) {
                    Fragment newFragment;
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    newFragment = new ImageFragment();
                    Bundle bundle = new Bundle();
                    String param1 = title.getText() + "+" + num.getText() + "+" + type.getText() + "+cover";
                    param1 = param1.trim();
                    param1 = param1.replace(" ", "+");
                    bundle.putString("param1", param1);
                    String param2 = title.getText() + "+" + num.getText();
                    param2 = param2.trim();
                    param2 = param2.replace(" ", "+");
                    bundle.putString("param2", param2);
                    newFragment.setArguments(bundle);
                    // Replace whatever is in the fragment_container view with this fragment,
                    transaction.replace(R.id.container, newFragment, "from_internet");
                    // and add the transaction to the back stack
                    transaction.addToBackStack("from_internet");
                    // Commit the transaction
                    transaction.commit();
                } else {
                    title.setError(getText(R.string.not_empty) + " para buscar capa");
                    type.setError(getText(R.string.not_empty) + " para buscar capa");
                }
                break;
            case R.id.camera:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                break;

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (((MainActivity) getActivity()).backBundle.get("url") != null) {
            this.url = String.valueOf(((MainActivity) getActivity()).backBundle.get("url"));
            this.setCover(url);
        }
        if (coverFile != null) {
            this.setCover(coverFile);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Bitmap bmp = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
//                cover.setImageBitmap(bitmap);
                this.saveCoverFromCamera("cover" + System.currentTimeMillis(), bitmap);
            }
        }
    }

    public void saveCoverFromCamera(String filename, Bitmap bmp) {
        DealWithFiles deal = new DealWithFiles();
        String coverpath = deal.saveBitmap(filename, bmp);
        File f = new File(coverpath);
        coverFile = f;
        this.setCover(f);
    }

    public void setCover(File f) {
        Picasso.with(view.getContext()).load(f)
                .fit().centerCrop()
                .into(cover);
    }

    public void setCover(String url) {
        Picasso.with(view.getContext()).load(url)
                .fit().centerCrop()
                .into(cover);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (this.coverFile != null) {
            outState.putString("file", this.coverFile.toString());
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        Log.wtf("onViewStateRestored", "aqui");
        if (savedInstanceState != null) {
            this.coverFile = new File(savedInstanceState.getString("file"));
        }
        super.onViewStateRestored(savedInstanceState);
    }

    public void Scroll(final int roll) {
        scroll.post(new Runnable() {
            @Override
            public void run() {
                scroll.fullScroll(roll);
            }
        });
    }

    public void comeIn(View v) {
        AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(1200);
        fadeIn.setFillAfter(true);
        v.startAnimation(fadeIn);
        v.setVisibility(View.VISIBLE);
    }

    public void goodBye(View v) {
        AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);
        fadeOut.setDuration(1200);
        v.setVisibility(View.GONE);
        fadeOut.setFillAfter(Boolean.TRUE);
        v.startAnimation(fadeOut);
        v.setClickable(Boolean.FALSE);
    }

    public void hideKeyboard() {
        View v = getActivity().getWindow().getCurrentFocus();
        if (v != null) {
            InputMethodManager imm = (InputMethodManager) getActivity()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
}

