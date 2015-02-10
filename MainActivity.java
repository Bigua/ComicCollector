package me.bigua.comiccollector;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import br.liveo.interfaces.NavigationLiveoListener;
import br.liveo.navigationliveo.NavigationLiveo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends NavigationLiveo implements NavigationLiveoListener {

    @Override
    public void onUserInformation() {
        //User information here
//        this.mUserName.setText("Rudson Lima");
//        this.mUserEmail.setText("rudsonlive@gmail.com");
//        this.mUserPhoto.setImageResource(R.drawable.ic_rudsonlive);
//        this.mUserBackground.setImageResource(R.drawable.ic_user_background);


        View mCustomHeader = getLayoutInflater().inflate(R.layout.custom_header_user, this.getListView(), false);
        ImageView imageView = (ImageView) mCustomHeader.findViewById(R.id.imageView);
        this.addCustomHeader(mCustomHeader); //This will add the new header and remove the default user header
    }


    @Override
    public void onInt(Bundle savedInstanceState) {
        //Creation of the list items is here

        // set listener {required}
        this.setNavigationListener(this);

        // name of the list items

        List<String> mListNameItem = new ArrayList<>();
        mListNameItem.add(0, "lalala");
        mListNameItem.add(1, "lalala");
        mListNameItem.add(2, "lalala");
        mListNameItem.add(3, "lalala");
        mListNameItem.add(4, "lalala"); //This item will be a subHeader
        mListNameItem.add(5, "lalala");
        mListNameItem.add(6, "lalala");

        // icons list items
        List<Integer> mListIconItem = new ArrayList<>();
        mListIconItem.add(0, 0);
        mListIconItem.add(1, 0); //Item no icon set 0
        mListIconItem.add(2, 0); //Item no icon set 0
        mListIconItem.add(3, 0);
        mListIconItem.add(4, 0); //When the item is a subHeader the value of the icon 0
        mListIconItem.add(5, 0);
        mListIconItem.add(6, 0);

        //{optional} - Among the names there is some subheader, you must indicate it here
        List<Integer> mListHeaderItem = new ArrayList<>();
        mListHeaderItem.add(4);

        //{optional} - Among the names there is any item counter, you must indicate it (position) and the value here

//        SparseIntArray mSparseCounterItem = new SparseIntArray(); //indicate all items that have a counter
//        mSparseCounterItem.put(0, 7);
//        mSparseCounterItem.put(6, 250);

        //If not please use the FooterDrawer use the setFooterVisible(boolean visible) method with value false
//        this.setFooterInformationDrawer(R.string.settings, R.drawable.ic_settings_black_24dp);
        this.setFooterNavigationVisible(false);
        this.setNavigationAdapter(mListNameItem, mListIconItem, mListHeaderItem, null);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClickUserPhotoNavigation(View view) {

    }

    @Override
    public void onClickFooterItemNavigation(View view) {

    }

    @Override
    public void onPrepareOptionsMenuNavigation(Menu menu, int i, boolean b) {

    }

    @Override
    public void onItemClickNavigation(int position, int container) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                fragmentTransaction.replace(R.id.container, MainFragment.newInstance(position + 1)).commit();
                break;
            case 1:
//                fragmentTransaction.replace(R.id.container, ProfileFragment.newInstance(position + 1)).commit();
                break;
            case 2:
//                fragmentTransaction.replace(R.id.container, PrescriptionFragment.newInstance(position + 1)).commit();
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
//                Intent intent = new Intent(this, LoginActivity.class);
//                startActivity(intent);
                finish();
                break;
        }

    }
}

