package com.example.ktask.model

import android.arch.lifecycle.LiveData
import android.content.Context

class PlaceRepository(context: Context) {

    var vm1 = PlaceAppDatabase.getDatabase(context)
    var placedao: PlaceDao = vm1!!.placedao()
    private var allData: LiveData<ModelPlaces>? = null
    var data2 : LiveData<List<ModelPlaces>>? =null
    var modelPlaces = ModelPlaces()

    fun insert(modelPlaces: ModelPlaces) : Long{
        var success : Long = 0
        Thread {
           success =  placedao.insert(modelPlaces)
        }.start()

        return success
    }

    fun select() {
        Thread {
            placedao.getList1()
        }.start()
    }

    fun delete(modelPlaces: ModelPlaces) : Int {
        var d : Int = 0
        Thread {
            placedao.deletePlace(modelPlaces)
        }.start()

        return d
    }

    fun deleteAllData(modelPlaces: ModelPlaces) {
        Thread {
            placedao.deletePlace(modelPlaces)
        }.start()
    }

    //    fun getAllData(modelPlaces: ModelPlaces) :LiveData<List<ModelPlaces>>
//    {
//        Thread{
//           // allData = placedao.getList()
//        }.start()
//
//        return allData
//    }
    fun getData(): LiveData<ModelPlaces> {

//        Thread {
            allData = placedao.getList()


//
//        }.start()
        return allData!!

    }

    fun getData1(): LiveData<List<ModelPlaces>> {


        data2 = placedao.getList1()

         return data2!!
    }

}

