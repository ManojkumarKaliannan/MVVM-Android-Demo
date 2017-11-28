package com.mvvmdemo.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mvvmdemo.R;
import com.mvvmdemo.databinding.ItemPeopleBinding;
import com.mvvmdemo.model.NameResponse;
import com.mvvmdemo.viewmodel.IteamViewModel;

import java.util.Collections;
import java.util.List;

/**
 * Created by Colan Infotech.
 */

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.DateViewHolder> {

    private List<NameResponse> peopleList;

    public PeopleAdapter() {
        this.peopleList = Collections.emptyList();
    }

    @Override
    public DateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
     /*   View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.
                item_people,
                parent, false);*/

        ItemPeopleBinding iteamViewModel = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_people,
                parent, false);

        return new DateViewHolder(iteamViewModel);

    }

    @Override
    public void onBindViewHolder(DateViewHolder holder, int position) {
        holder.bindPeople(peopleList.get(position));
    }

    @Override
    public int getItemCount()
    {
        return peopleList.size();
    }

    public void setPeopleList(List<NameResponse> peopleList) {
        this.peopleList = peopleList;
        notifyDataSetChanged();

    }

    public class DateViewHolder extends RecyclerView.ViewHolder {

        ItemPeopleBinding itemPeopleBinding;

        public DateViewHolder(ItemPeopleBinding itemPeopleBinding) {
            super(itemPeopleBinding.itemPeople);
            this.itemPeopleBinding = itemPeopleBinding;
        }

        public void bindPeople(NameResponse nameResponse)
        {
            if (itemPeopleBinding.getPeopleViewModel() == null)
            {
                itemPeopleBinding.setPeopleViewModel(
                        new IteamViewModel(nameResponse, itemView.getContext()));
            } else
            {
                itemPeopleBinding.getPeopleViewModel().setPeople(nameResponse);
            }
        }
    }
}
