package me.bigua.comiccollector.Fragments;


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
import com.tokenautocomplete.TokenCompleteTextView;
import me.bigua.comiccollector.AbstBase.DataProxy;
import me.bigua.comiccollector.AbstBase.Handlers.AuthorHandlers;
import me.bigua.comiccollector.*;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFormFragment extends Fragment implements View.OnClickListener, TokenCompleteTextView.TokenListener {


    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private File coverFile;
    private View view;
    private Switch wish, complete;
    private EditText title, num, type, galaxy, universe, publi, year, lang;
    private TextView fields;
    private ImageView cover;
    private String url;
    private LinearLayout layout;
    private ScrollView scroll;

    private AuthorCompletionView authors;


    public AddFormFragment() {
        // Required empty public constructor
    }

    public static AddFormFragment newInstance(int sectionNumber) {
        AddFormFragment fragment = new AddFormFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_form, container, false);

        title = (EditText) view.findViewById(R.id.comic_title);
        num = (EditText) view.findViewById(R.id.num_comic);
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
        authors = (AuthorCompletionView) view.findViewById(R.id.searchView);

        from_internet.setOnClickListener(this);
        camera.setOnClickListener(this);
        fields.setOnClickListener(this);

        AuthorHandlers authorHandlers = new AuthorHandlers(getActivity().getBaseContext());

        TokenAdapter tokenAdapter = new TokenAdapter(getActivity().getBaseContext(), R.layout.author_layout, authorHandlers.List());

        authors.setAdapter(tokenAdapter);
        authors.setTokenListener(this);


        setHasOptionsMenu(true);
        ((MainActivity) getActivity()).setActionBarTitle(R.string.add_comic);
        return view;
    }


    private void getValues(View view) {
        Map<String, String> raw = new HashMap<>();
        if (wish.isChecked()) {
            raw.put("wishlist", "true");
        }

        if (complete.isChecked()) {
            raw.put("complete", "true");
        } else {
            raw.put("complete", "false");
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

//        if (StringUtils.isNotBlank(author.getText())) {
//            raw.put("author", author.getText().toString().trim());
//        }

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
        if (this.coverFile != null) {
            raw.put("cover", this.coverFile.toString());
        }
        this.saveComic(view, raw);
    }

    private void saveComic(View view, Map<String, String> raw) {
        DataProxy dataproxy = new DataProxy(view.getContext());
        dataproxy.persistComic(raw);
        Scroll(ScrollView.FOCUS_UP);
        this.cleanFields();
        Toast toast = Toast.makeText(getActivity(), "Salvo com sucesso", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();
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
                    this.getCoversFromGoogle();
                } else {
                    if (StringUtils.isBlank(title.getText())) {
                        title.setError(getText(R.string.not_empty));
                    }
                    if (StringUtils.isBlank(type.getText())) {
                        type.setError(getText(R.string.not_empty));
                    }
                }
                break;
            case R.id.camera:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                break;
        }
    }

    private void getCoversFromGoogle() {
        android.support.v4.app.Fragment newFragment;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        newFragment = new CoverFragment();
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
        transaction.replace(R.id.container, newFragment, "from_internet");
        transaction.addToBackStack("from_internet");
        transaction.commit();
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

    private void saveCoverFromCamera(String filename, Bitmap bmp) {
        DealWithFiles deal = new DealWithFiles();
        String coverpath = deal.saveBitmap(filename, bmp);
        File f = new File(coverpath);
        this.coverFile = f;
        this.setCover(f);
    }

    private void setCover(File f) {
        this.url = null;
        Picasso.with(view.getContext()).load(f)
                .fit().centerCrop()
                .into(cover);
    }

    private void setCover(String url) {
        this.coverFile = null;
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
        if (savedInstanceState != null) {
            this.coverFile = new File(savedInstanceState.getString("file"));
        }
        super.onViewStateRestored(savedInstanceState);
    }

    private void cleanFields() {
        title.setText("");
        num.setText("");
        type.setText("");
        authors.setText("");
        galaxy.setText("");
        universe.setText("");
        publi.setText("");
        year.setText("");
        lang.setText("");
        wish.setChecked(false);
        complete.setChecked(false);
        coverFile = null;
        url = null;
        this.setCover("null");
    }

    private void Scroll(final int roll) {
        scroll.post(new Runnable() {
            @Override
            public void run() {
                scroll.fullScroll(roll);
            }
        });
    }

    private void comeIn(View v) {
        AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(1200);
        fadeIn.setFillAfter(Boolean.TRUE);
        v.startAnimation(fadeIn);
        v.setClickable(Boolean.FALSE);
        v.setVisibility(View.VISIBLE);
    }

    private void goodBye(View v) {
        AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);
        fadeOut.setDuration(1200);
        fadeOut.setFillAfter(Boolean.TRUE);
        v.startAnimation(fadeOut);
        v.setClickable(Boolean.FALSE);
        v.setVisibility(View.GONE);
    }

    private void hideKeyboard() {
        View v = getActivity().getWindow().getCurrentFocus();
        if (v != null) {
            InputMethodManager imm = (InputMethodManager) getActivity()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    private void updateTokenConfirmation() {
        StringBuilder sb = new StringBuilder("Current tokens:\n");
        for (Object token : authors.getObjects()) {
            sb.append(token.toString());
            sb.append("\n");
        }
        //  ((TextView)findViewById(R.id.tokens)).setText(sb);
    }

    @Override
    public void onTokenAdded(Object token) {
//        ((TextView)findViewById(R.id.lastEvent)).setText("Added: " + token);
//        updateTokenConfirmation();
//        System.out.println();
        Log.wtf("aqui", "nessa merda");
    }

    @Override
    public void onTokenRemoved(Object token) {
        //((TextView)findViewById(R.id.lastEvent)).setText("Removed: " + token);
        //updateTokenConfirmation();
    }

}
