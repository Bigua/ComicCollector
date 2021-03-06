package me.bigua.comiccollector;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import br.liveo.interfaces.NavigationLiveoListener;
import br.liveo.navigationliveo.NavigationLiveo;
import me.bigua.comiccollector.Fragments.AddFormFragment;
import me.bigua.comiccollector.Fragments.ListFragment;
import me.bigua.comiccollector.Fragments.WishListFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends NavigationLiveo implements NavigationLiveoListener {


    public Bundle backBundle = new Bundle();

    @Override
    public void onUserInformation() {
//        User information here
//        this.mUserName.setText("Rudson Lima");
//        this.mUserEmail.setText("rudsonlive@gmail.com");
//        this.mUserPhoto.setImageResource(R.drawable.ic_rudsonlive);
//        this.mUserBackground.setImageResource(R.drawable.ic_rudsonlive);


        View mCustomHeader = getLayoutInflater().inflate(
                R.layout.custom_header_user,
                this.getListView(), false
        );
        ImageView imageView = (ImageView) mCustomHeader.findViewById(R.id.imageView);
//        //This will add the new header and remove the default user header
        this.addCustomHeader(mCustomHeader);
    }


    @Override
    public void onInt(Bundle savedInstanceState) {
        //Creation of the list items is here

        // set listener {required}
        this.setNavigationListener(this);
        this.setDefaultStartPositionNavigation(1);
        // name of the list items

        List<String> mListNameItem = new ArrayList<String>();
        mListNameItem.add(0, getString(R.string.add_comic));
        mListNameItem.add(1, getString(R.string.list_comics));
        mListNameItem.add(2, getString(R.string.whish_list));

        // icons list items
        List<Integer> mListIconItem = new ArrayList<Integer>();
        mListIconItem.add(0, 0);
        mListIconItem.add(1, 0);
        mListIconItem.add(2, 0);
        mListIconItem.add(3, 0);
        mListIconItem.add(4, 0);
        mListIconItem.add(5, 0);
        mListIconItem.add(6, 0);

        //{optional} - Among the names there is some subheader, you must indicate it here
//        List<Integer> mListHeaderItem = new ArrayList<Integer>();
//        mListHeaderItem.add(4);

        //{optional} - Among the names there is any item counter, you must indicate it (position) and the value here

//        SparseIntArray mSparseCounterItem = new SparseIntArray(); //indicate all items that have a counter
//        mSparseCounterItem.put(0, 7);
//        mSparseCounterItem.put(6, 250);

        //If not please use the FooterDrawer use the setFooterVisible(boolean visible) method with value false
//        this.setFooterInformationDrawer(R.string.settings, R.drawable.ic_settings_black_24dp);
        this.setFooterNavigationVisible(false);
        this.setNavigationAdapter(mListNameItem, mListIconItem, null, null);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is comeIn.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final MenuItem menuItem = menu.findItem(R.id.menu_add);
        menuItem.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClickNavigation(int position, int container) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        switch (position) {
            case 0:
                fragmentTransaction.replace(R.id.container,
                        AddFormFragment.newInstance(position + 1))
                        .commit();
                break;
            case 1:
                fragmentTransaction.replace(R.id.container,
                        ListFragment.newInstance(position + 1))
                        .commit();
                break;
            case 2:
                fragmentTransaction.replace(R.id.container,
                        WishListFragment.newInstance(position + 1))
                        .commit();
                break;
        }

    }

    public void setActionBarTitle(int title) {
        getSupportActionBar().setTitle(title);
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
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void putInBundle(String la, String le) {
        backBundle.putString(la, le);
    }
}

