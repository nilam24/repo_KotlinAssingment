package com.example.ktask.model

import android.util.Log
import com.example.ktask.PlaceApiClient
import com.example.ktask.PlaceApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaceDataManager {

    companion object {

        fun newInstance(): PlaceDataManager {
            return PlaceDataManager()
        }
    }

    var id:String = ""
    var name : String = ""
    var ll :String = "40.7484,-73.9857"
    var authToken :String = "NPKYZ3WZ1VYMNAZ2FLX1WLECAWSMUVOQZOIDBN53F3LVZBPQ"
    var v : String = "20180616"
    var ap : PlaceApiInterface? =null
    var modelPlaces = ModelPlaces()
    var list2 = arrayListOf<ModelPlaces>()

    fun getPlace() : List<ModelPlaces> {

        ap = PlaceApiClient().getPlaceApiClient().create(PlaceApiInterface::class.java)

        var call = ap?.getPlace(ll,authToken,v) as Call<ModelPlaces>


        call.enqueue(object : Callback<ModelPlaces> {
            override fun onFailure(call: Call<ModelPlaces>, t: Throwable) {
                Log.e(""+t.printStackTrace(),"@@@")
            }

            override fun onResponse(call: Call<ModelPlaces>, response: Response<ModelPlaces>) {
                if(response.isSuccessful) {
                    response.body()

                    // list2=list
                    Log.e(""+response.body(),"@@")
                }
                //    else response.errorBody()
                //   Log.e("","@@"+response.errorBody())
            }
        })


        return list2

    }

}