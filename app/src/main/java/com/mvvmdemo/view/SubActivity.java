package com.mvvmdemo.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.mvvmdemo.R;
import com.mvvmdemo.databinding.ActivitySubBinding;
import com.mvvmdemo.viewmodel.NameViewModel;
import com.mvvmdemo.viewmodel.SubActivityDetailViewModel;

public class SubActivity extends AppCompatActivity {

    ActivitySubBinding activtySubBindling;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setting view model

        initDataBinding();
        setSupportActionBar(activtySubBindling.toolbar);
        getFragmentLayout();

    }



    private void initDataBinding()
    {
        activtySubBindling = DataBindingUtil.setContentView(this, R.layout.activity_sub);

    }

    private void getFragmentLayout()
    {
        SubFragment pickUpLocationMapsFragment = new SubFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().replace(R.id.frame, pickUpLocationMapsFragment);
        fragmentTransaction.commit();
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
}
