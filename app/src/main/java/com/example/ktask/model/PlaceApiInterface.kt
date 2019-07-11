package com.example.ktask

import com.example.ktask.model.ModelPlaces
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PlaceApiInterface {

    @GET("v2/venues/search")
    fun getPlace(@Query("ll") ll : String,
                 @Query("oauth_token") oauth_token : String,
                 @Query("v") v : String) : Call<ModelPlaces>
    //fun getPlace() : Call<ModelPlaces>

}

//@Query("ll") ll : String,
// ?ll=40.7484,-73.9857&oauth_token=NPKYZ3WZ1VYMNAZ2FLX1WLECAWSMUVOQZOIDBN53F3LVZBPQ                  @Query("oauth_token") oauth_token : String,