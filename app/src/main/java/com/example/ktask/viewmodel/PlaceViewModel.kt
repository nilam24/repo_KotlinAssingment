package com.example.ktask.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.ktask.model.ModelPlaces
import com.example.ktask.model.PlaceRepository

class PlaceViewModel(application: Application) :IPlaceViewModel, AndroidViewModel(application){

    var modelPlaces = ModelPlaces()
    var id : String = ""
    var name : String =""

    var placeRepository = PlaceRepository(application)
    var allData : LiveData<ModelPlaces> = placeRepository.getData()
    var allDataList : LiveData<List<ModelPlaces>> =placeRepository.getData1()


    override fun insert(modelPlaces: ModelPlaces) : Long {
        var success : Long = 0
        success = placeRepository.insert(modelPlaces)
        return success
    }

    override fun select(): LiveData<ModelPlaces> {

         allData = placeRepository.getData()

        return allData!!
    }

    override fun delete(modelPlaces: ModelPlaces) : Int {
        var i : Int =0
        placeRepository.deleteAllData(modelPlaces)
        return i
    }

    override fun select1(): LiveData<List<ModelPlaces>> {

       allDataList = placeRepository.getData1()
        return allDataList
    }



    //    fun insert(modelPlaces: ModelPlaces){
//        placeRepository.insert(modelPlaces)
//    }

//    fun delete(){
//        placeRepository.delete(modelPlaces)
//    }
//    fun getAll(){
//        allData = placeRepository.getAllData(modelPlaces)
//    }
}