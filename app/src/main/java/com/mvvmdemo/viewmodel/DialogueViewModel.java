package com.mvvmdemo.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mvvmdemo.Interface.ApiInterface;
import com.mvvmdemo.R;
import com.mvvmdemo.model.NameResponse;
import com.mvvmdemo.webservice.WebServiceClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Colan Infotech.
 */

public class DialogueViewModel extends Observable
{
    Context context;
    public ObservableField<String> messageText;
    public ObservableInt peopleRecycler;
    public ObservableInt peopleProgress;
    public ObservableInt peopleLabel;
    private List peopleList;
    public DialogueViewModel(Context context)
    {
        this.context=context;
        this.peopleList = new ArrayList<>();
        peopleLabel = new ObservableInt(View.VISIBLE);
        peopleProgress=new ObservableInt(View.VISIBLE);
        messageText = new ObservableField<>(context.getString(R.string.mvvmtext));
        peopleRecycler=new ObservableInt(View.GONE);
        getListResponse();

    }
    private void getListResponse()
    {
        ApiInterface apiInterface = WebServiceClient.getClient().create(ApiInterface.class);
        Call<List<NameResponse>> call = apiInterface.getAllListDetails();
        call.enqueue(new Callback<List<NameResponse>>() {
            @Override
            public void onResponse(Call<List<NameResponse>> call, Response<List<NameResponse>> response) {

                if(response.body()!=null)
                {


                        peopleProgress.set(View.GONE);
                        peopleLabel.set(View.GONE);
                        peopleRecycler.set(View.VISIBLE);
                       changePeopleDataSet(response.body());

                }
                else
                {
                    peopleProgress.set(View.GONE);
                    Toast.makeText(context,"failure1",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<NameResponse>> call, Throwable t)
            {
                peopleProgress.set(View.GONE);
                Toast.makeText(context,"failure2",Toast.LENGTH_SHORT).show();
                Log.d("erorr",t.getMessage());
            }
        });
    }

    private void changePeopleDataSet(List<NameResponse> body)
    {
        peopleList.addAll(body);
        setChanged();
        notifyObservers();
    }

    public List<NameResponse> getPeopleListDialogue() {
        return peopleList;
    }



}
