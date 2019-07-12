package com.example.ktask.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.*

@Dao
interface PlaceDao  {

     @Query("SELECT * FROM  ModelPlaces LIMIT 10")
     fun  getList() : LiveData<ModelPlaces>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(modelPlaces: ModelPlaces) : Long

    @Delete
    fun deletePlace(modelPlaces: ModelPlaces) : Int

    @Query("SELECT * FROM  ModelPlaces" )
    fun  getList1() : LiveData<List<ModelPlaces>>
    @Update
    fun refreshDb(modelPlaces: ModelPlaces)

}