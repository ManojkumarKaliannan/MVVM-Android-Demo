package com.mvvmdemo.viewmodel;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mvvmdemo.R;
import com.mvvmdemo.databinding.DialoguenamePopupBinding;
import com.mvvmdemo.databinding.FragmentMainBinding;
import com.mvvmdemo.view.DialogueActivity;
import com.mvvmdemo.view.PeopleAdapter;
import com.mvvmdemo.view.SubFragment;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Colan Infotech.
 */

public class FragmentViewModel extends Observable
{

    Context context;

    public FragmentViewModel(Context context)
    {
      this. context=context;
    }

    public void onClickLoad(View view) {


        DialogueActivity mydialog = new DialogueActivity(context);
        mydialog.setTitle("PopupList");
        mydialog.show();

    }




}
