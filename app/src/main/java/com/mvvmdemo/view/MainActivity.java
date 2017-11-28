package com.mvvmdemo.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.mvvmdemo.R;
import com.mvvmdemo.databinding.ActivityMainBinding;
import com.mvvmdemo.viewmodel.NameViewModel;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {

    ActivityMainBinding activityMainBinding;
    NameViewModel nameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setting view model
        initDataBinding();
        setSupportActionBar(activityMainBinding.toolbar);
        setupObserver(nameViewModel);
        setupListPeopleView(activityMainBinding.listPeople);

    }



    private void initDataBinding() {
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        nameViewModel=new NameViewModel(this);
        activityMainBinding.setNameViewModel(nameViewModel);
    }


    private void setupObserver(Observable observable) {
        observable.addObserver(this);
    }
    private void setupListPeopleView(RecyclerView listPeople) {
        PeopleAdapter adapter = new PeopleAdapter();
        listPeople.setAdapter(adapter);
        listPeople.setLayoutManager(new LinearLayoutManager(this));
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
    public void update(Observable observable, Object arg) {

        if (observable instanceof NameViewModel)
        {
            PeopleAdapter peopleAdapter = (PeopleAdapter) activityMainBinding.listPeople.getAdapter();
            NameViewModel nameViewModel = (NameViewModel) observable;
            peopleAdapter.setPeopleList(nameViewModel.getPeopleList());
        }
    }
}
