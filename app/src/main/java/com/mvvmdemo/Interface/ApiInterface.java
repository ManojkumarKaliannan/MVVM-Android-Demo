package com.mvvmdemo.Interface;

import com.mvvmdemo.model.NameResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Colan Infotech.
 */

public interface ApiInterface
{
    //getAllCountries
    @GET("photos")
    Call<List<NameResponse>> getAllListDetails();
}
