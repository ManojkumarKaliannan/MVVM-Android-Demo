package com.mvvmdemo.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mvvmdemo.Interface.ApiInterface;
import com.mvvmdemo.R;
import com.mvvmdemo.webservice.WebServiceClient;
import com.mvvmdemo.model.NameResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**`
 * Created by Colan Infotech.
 */

public class NameViewModel extends Observable  {

    public ObservableInt peopleLabel;
    public ObservableField<String> messageLabel;
    public ObservableInt peopleProgress;
    public ObservableInt peopleRecycler;
    private Context context;
    private List peopleList;

    public NameViewModel(Context context)
    {
        this.context=context;
        this.peopleList = new ArrayList<>();
        peopleLabel = new ObservableInt(View.VISIBLE);
        peopleProgress=new ObservableInt(View.VISIBLE);
        peopleRecycler=new ObservableInt(View.GONE);
        messageLabel = new ObservableField<>(context.getString(R.string.mvvmtext));
        getListResponse();
    }

   /* public void onClickFabLoad(View view)
    {

       //  getListResponse();

    }*/

    private void getListResponse()
    {
        ApiInterface apiInterface = WebServiceClient.getClient().create(ApiInterface.class);
        Call<List<NameResponse>> call = apiInterface.getAllListDetails();
        call.enqueue(new Callback<List<NameResponse>>() {
            @Override
            public void onResponse(Call<List<NameResponse>> call, Response<List<NameResponse>> response) {

                if(response.body()!=null)
                {

                 /*   peopleList=response.body();
                    peopleList.addAll(response.body());*/
                    peopleProgress.set(View.GONE);
                    peopleLabel.set(View.GONE);
                    peopleRecycler.set(View.VISIBLE);
                    changePeopleDataSet(response.body());
                    Toast.makeText(context,"sucess",Toast.LENGTH_SHORT).show();
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

    public List<NameResponse> getPeopleList() {
        return peopleList;
    }




}
