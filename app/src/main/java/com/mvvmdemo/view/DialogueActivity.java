package com.mvvmdemo.view;


import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Window;
import com.mvvmdemo.R;
import com.mvvmdemo.databinding.DialoguenamePopupBinding;
import com.mvvmdemo.viewmodel.DialogueViewModel;
import com.mvvmdemo.viewmodel.NameViewModel;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Colan Infotech.
 */

public class DialogueActivity extends Dialog implements Observer {

    DialoguenamePopupBinding fragmentMainBinding;
    DialogueViewModel dialogueViewModel;

    Context context;
    public DialogueActivity(@NonNull Context context) {
        super(context);
        this.context=context;

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      //  requestWindowFeature(Window.FEATURE_NO_TITLE);
        fragmentMainBinding= DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialoguename_popup, null, false);
        dialogueViewModel=new DialogueViewModel(getContext());
        fragmentMainBinding.setDialogueDetailsViewModel(dialogueViewModel);
        setContentView(fragmentMainBinding.getRoot());
        setupObserver(dialogueViewModel);
        setupListPeopleView(fragmentMainBinding.listDialoguepeople);

    }

    private void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    private void setupListPeopleView(RecyclerView listDialoguepeople)
    {
        PeopleAdapter adapter = new PeopleAdapter();
        listDialoguepeople.setAdapter(adapter);
        listDialoguepeople.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public void update(Observable observable, Object arg)
    {

        PeopleAdapter peopleAdapter = (PeopleAdapter) fragmentMainBinding.listDialoguepeople.getAdapter();
        DialogueViewModel dialogueViewModel = (DialogueViewModel) observable;
        peopleAdapter.setPeopleList(dialogueViewModel.getPeopleListDialogue());

    }
}
