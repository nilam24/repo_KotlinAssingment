package com.example.ktask.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.util.Log
import com.example.ktask.PlaceApiClient
import com.example.ktask.PlaceApiInterface
import com.example.ktask.model.ModelPlaces
import com.example.ktask.model.PlaceAppDatabase
import com.example.ktask.model.PlaceDao
import com.example.ktask.model.PlaceDataManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

class ViewModel1(application: Application) : AndroidViewModel(application) {
    var vm1 = PlaceAppDatabase.getDatabase(application)
    var placedao: PlaceDao = vm1!!.placedao()
    private var allData: LiveData<ModelPlaces>? = null
    var allDataList: LiveData<List<ModelPlaces>>? = null
    var modelPlaces = ModelPlaces()
    lateinit var dataManager : PlaceDataManager
    var ap : PlaceApiInterface? =null
    lateinit var apiList : List<ModelPlaces>

    fun insert(modelPlaces: ModelPlaces) {
        placedao.insert(modelPlaces)

    }

    fun select1(): LiveData<List<ModelPlaces>> {

            allDataList = placedao.getList1()

        return allDataList!!
    }

    fun delete(modelPlaces: ModelPlaces): Int {

        var success = 0
        thread(start = true) {
            success = placedao.deletePlace(modelPlaces)
        }
        return success

    }
    fun updateFun(modelPlaces: ModelPlaces){
        placedao.refreshDb(modelPlaces)
    }

    fun apiCall(context: Context,ll:String,oauth_token:String,v:String) : LiveData<ModelPlaces>{
        var apicalllist:LiveData<ModelPlaces>? =null

        ap = PlaceApiClient().getPlaceApiClient().create(PlaceApiInterface::class.java)
        var call = ap?.getPlace(ll,oauth_token,v) as Call<ModelPlaces>
        var mps = ModelPlaces()
        call.enqueue(object : Callback<ModelPlaces> {
            override fun onFailure(call: Call<ModelPlaces>, t: Throwable) {
                Log.e(""+t.printStackTrace(),"@@@")
            }

            override fun onResponse(call: Call<ModelPlaces>, response: Response<ModelPlaces>) {
                if(response.isSuccessful) {
                  mps = response.body()!!
                   mps.placeId
                    mps.placeName
                    response.errorBody()
                   // apicalllist.value = response.body()

                    Log.e("","@@"+response.errorBody())

                }
                //    else response.errorBody()

            }
        })

        return apicalllist!!

        //apiList = dataManager.getPlace()
        //Log.e("retrofit call",""+apiList)
    }
}