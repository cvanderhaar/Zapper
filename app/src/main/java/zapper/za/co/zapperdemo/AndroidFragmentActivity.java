package zapper.za.co.zapperdemo;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

public class AndroidFragmentActivity extends Activity
        implements ListFragment.OnURLSelectedListener {

    boolean detailPage = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("AndroidFragmentActivity", "onCreate()");
        Log.v("AndroidFragmentsavedInstanceState", savedInstanceState == null ? "true" : "false");

        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ListFragment listFragment = new ListFragment();
            ft.add(R.id.displayList, listFragment, "List_Fragment");
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }

        if(findViewById(R.id.displayDetail) != null){
            detailPage = true;
            getFragmentManager().popBackStack();

            DetailFragment detailFragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.displayDetail);
            if(detailFragment == null){
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                detailFragment = new DetailFragment();
                ft.replace(R.id.displayDetail, detailFragment, "Detail_Fragment1");
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        }

    }


    @Override
    public void onURLSelected(String URL) {
        Log.v("AndroidFragmentActivity",URL);

        if(detailPage){
            DetailFragment detailFragment = (DetailFragment)
                    getFragmentManager().findFragmentById(R.id.displayDetail);
            detailFragment.updateURLContent(URL);
        }
        else{
            DetailFragment detailFragment = new DetailFragment();
            detailFragment.setURLContent(URL);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.displayList, detailFragment, "Detail_Fragment2");
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

}