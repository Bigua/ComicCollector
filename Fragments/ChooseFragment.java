package me.bigua.comiccollector.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import me.bigua.comiccollector.MainActivity;
import me.bigua.comiccollector.R;
//import com.google.zxing.integration.android.IntentIntegrator;
//import com.google.zxing.integration.android.IntentResult;

/**
 * Created by Bigua on 3/1/15.
 * bigua.kun@gmail.com
 */
public class ChooseFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static ChooseFragment newInstance(int sectionNumber) {
        ChooseFragment fragment = new ChooseFragment();
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
                R.layout.fragment_choose,
                container, false);
        Button barcode = (Button) view.findViewById(R.id.barcode);
        Button manual = (Button) view.findViewById(R.id.manual);
        manual.setOnClickListener(this);
        barcode.setOnClickListener(this);

        ((MainActivity) getActivity()).setActionBarTitle(R.string.choose_comic);

        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//        if (scanResult != null) {
//            String contents = scanResult.getContents();
//            if (contents != null)
//                Log.wtf("aqui", " barcode");
//            else
//                finish();
//        }
    }

    private void finish() {
        Log.wtf("finish", "him");
    }

    @Override
    public void onClick(View v) {
        // Create new fragment and transaction
        Fragment newFragment;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        switch (v.getId()) {

            case R.id.manual:
                newFragment = new AddFormFragment();
                // Replace whatever is in the fragment_container view with this fragment,
                transaction.replace(R.id.container, newFragment);
                // and add the transaction to the back stack
                transaction.addToBackStack("add");
                // Commit the transaction
                transaction.commit();
                break;
            case R.id.barcode:
//                IntentIntegrator.forSupportFragment(this).initiateScan();
                break;

        }


    }
}