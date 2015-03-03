package me.bigua.comiccollector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Bigua on 3/1/15.
 * bigua.kun@gmail.com
 */
public class ChooseFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private Button barcode;
    private Button manual;

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
        barcode = (Button) view.findViewById(R.id.barcode);
        manual = (Button) view.findViewById(R.id.manual);
        manual.setOnClickListener(this);

        return view;
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.manual:
                // Create new fragment and transaction
                Fragment newFragment = new AddFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                // Replace whatever is in the fragment_container view with this fragment,
                transaction.replace(R.id.container, newFragment);
                // and add the transaction to the back stack
                transaction.addToBackStack("add");
                // Commit the transaction
                transaction.commit();
                break;
        }
    }
}
