package com.mvvmdemo.view;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mvvmdemo.R;
import com.mvvmdemo.databinding.FragmentMainBinding;
import com.mvvmdemo.viewmodel.DialogueViewModel;
import com.mvvmdemo.viewmodel.FragmentViewModel;

/**
 * A placeholder fragment containing a simple view.
 */
public class SubFragment extends Fragment {

    FragmentMainBinding fragmentMainBinding;
    FragmentViewModel fragmentViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentMainBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_main, container, false);

        fragmentViewModel=new FragmentViewModel(getContext());
        fragmentMainBinding.setFragmentNameModel(fragmentViewModel);

        return fragmentMainBinding.getRoot();
    }



}
