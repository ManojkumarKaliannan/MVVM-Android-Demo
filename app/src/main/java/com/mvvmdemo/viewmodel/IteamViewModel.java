package com.mvvmdemo.viewmodel;

import android.content.Context;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.mvvmdemo.model.NameResponse;
import com.mvvmdemo.view.SubActivity;

import java.util.Observable;

/**
 * Created by Colan Infotech.
 */

public class IteamViewModel  extends Observable
{
    private NameResponse people;
    private Context context;

    public IteamViewModel(NameResponse people, Context context) {
        this.people = people;
        this.context = context;
    }
    public String getFullName()
    {


        return people.getTitle();
    }


    public String getMail()
    {
        return people.getThumbnailUrl();
    }

    public String getPictureProfile() {
        return people.getUrl();
    }


    @BindingAdapter("imageUrl") public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
    public void setPeople(NameResponse people) {
        this.people = people;

    }

    public void onItemClick(View view) {


        Intent intent = new Intent(context, SubActivity.class);
        context.startActivity(intent);
    }





}
